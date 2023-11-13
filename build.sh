docker exec spigot_dev chmod -R 777 /minecraft/world
#rm -rf /home/shai/valentin-minecraft/dev/world
mv ./target/CS3-1.0-SNAPSHOT.jar /home/shai/valentin-minecraft/dev-1.19.2/plugins/
clear
docker compose -f /home/shai/valentin-minecraft/docker-compose.yml restart dev; docker logs -f spigot_dev -n 15
