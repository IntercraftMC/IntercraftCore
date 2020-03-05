#!/usr/bin/env bash

NAME="crowbar"

#MATCHERS=("ingot" "nugget" "dust" "dustsmall" "plate" "gear" "rod")
#MATCHERS=("al" "cu" "au" "ir" "fe" "pb" "li" "hg" "ag" "th" "sn" "ti" "w" "u" "zn" "cuzn" "cusn" "fec")
MATCHERS=("white" "orange" "magenta" "light_blue" "yellow" "lime" "pink" "gray" "light_gray" "cyan" "purple" "blue" "brown" "green" "red" "black")

for VAR in ${MATCHERS[*]}
do
    STRUCTURE="\
    {
      \"parent\": \"item/handheld\",
      \"textures\": {
        \"layer0\": \"intercraftcore:item/crowbar\",
        \"layer1\": \"intercraftcore:item/crowbar_overlay\"
      }
    }
    "
    echo "Creating file "${VAR}"_"${NAME}".json"
    echo $STRUCTURE > ${VAR}"_"${NAME}".json"
done
