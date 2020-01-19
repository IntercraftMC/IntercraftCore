#!/usr/bin/env bash

NAME="agau"

MATCHERS=("ingot" "nugget" "dust" "dustsmall" "plate" "gear" "rod")
#MATCHERS=("al" "cu" "au" "ir" "fe" "pb" "li" "hg" "ag" "th" "sn" "ti" "w" "u" "zn" "cuzn" "cusn" "fec")
#MATCHERS=("white" "orange" "magenta" "light_blue" "yellow" "lime" "pink" "gray" "light_gray" "cyan" "purple" "blue" "brown" "green" "red" "black")

for VAR in ${MATCHERS[*]}
do
    STRUCTURE="\
    {
      \"parent\": \"item/generated\",
      \"textures\": {
        \"layer0\": \"intercraftcore:item/resources/${VAR}\"
      }
    }
    "
    echo "Creating file "${NAME}"_"${VAR}".json"
    echo $STRUCTURE > ${NAME}"_"${VAR}".json"
done
