package com.drgnman.management_server_gradle.common;
import com.drgnman.management_server_gradle.Repository.DataRepository;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CommonCalculation {
    final static ModelMapper modelMapper = new ModelMapper();

    // region レコードのタイムスタンプからパブリッシュ頻度を計算する
    public int calcAverage(DataRepository dataRepository, String topicId) {
        try {
            // トピックIDが一致するレコードを全取得
            List<Object> resultList = dataRepository.TimestampOfSearchByTopicId(topicId);
            List<Integer> timeList = null;
            int ave_time = 0;
            long f_time = 0;
            long n_time = 0;
            int size = resultList.size();

            // もしリストの要素数が2未満の場合は、規定値を返す
            if (size < 2) {
                return 100;
            }

            for (int i=0; i < resultList.size()-1; i++) {
                // Object -> DTOの変換処理
                int length = Array.getLength(resultList.get(i));
                List f_list = new ArrayList();
                List n_list = new ArrayList();
                for (int j=0; j<length; j++) {
                    f_list.add(Array.get(resultList.get(i), j));
                    n_list.add(Array.get(resultList.get(i+1), j));
                }
                // X番目のpublishタイムの時間
                if (f_list.get(1) != null) {
                    // pub_timestampを格納
                    f_time = ((BigInteger)f_list.get(1)).longValue();
                }
                else {
                    // DBに登録された時間をpub_timestampとして格納
                    f_time = ((BigInteger)f_list.get(2)).longValue();
                }

                // X+1番目のpublishタイムの時間
                if (n_list.get(1) != null) {
                    // pub_timestampを格納
                    n_time = ((BigInteger)n_list.get(1)).longValue();
                }
                else {
                    // DBに登録された時間をpub_timestampとして格納
                    n_time = ((BigInteger)n_list.get(2)).longValue();
                }
                // dataの重複計算防止 f_timeとn_timeが同じ場合は平均値計算から省く
                if(f_time == n_time) {
                    size -= 1;
                } else {
                    ave_time += f_time - n_time;
                }
            }

            if (size -1 < 2) {
                return 100;
            }
            // Publish時間感覚を算出する
            ave_time = ave_time/(size - 1);

            return ave_time;

        } catch (Exception e) {
            // 算出に失敗した場合は-1値をエラーとして返しておく
            System.out.println("Error: " + e);
            return -1;
        }
    }
    // endregion

}
