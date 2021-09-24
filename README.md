# SOXStore-Server

# install docker
edit docker-compose.yml and change password (suggested) and ports (if necessary)
open a terminal and change the workdir to ./soxfire

# create images
run  docker-compose build

# start service
run docker-compose up

initialize soxfire via webgui at http://[host-name]:9090
following the 3.Configuration at https://sox.ht.sfc.keio.ac.jp/soxfire/index.html
POINT:  set the host-name to "dbserver" in the database configure
