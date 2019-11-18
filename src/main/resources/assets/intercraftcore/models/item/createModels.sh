#!/usr/bin/env bash

NAME="bucket"
FLUID="rubber_resin"

#MATCHERS=("ingot" "nugget" "dust" "dustsmall" "plate" "gear" "rod")
#MATCHERS=("al" "cu" "au" "ir" "fe" "pb" "li" "hg" "ag" "th" "sn" "ti" "w" "u" "zn" "cuzn" "cusn" "fec")
MATCHERS=("oak" "spruce" "birch" "jungle" "acacia" "dark_oak")

for VAR in ${MATCHERS[*]}
do
    STRUCTURE="\
    {
      \"parent\": \"item/generated\",
      \"textures\": {
        \"layer0\": \"intercraftcore:item/buckets/${VAR}_${NAME}\",
        \"layer1\": \"intercraftcore:item/buckets/fluid_${FLUID}\"
      }
    }
    "
    echo "Creating file "$FLUID"_"$VAR"_"$NAME".json"
    echo $STRUCTURE > $FLUID"_"$VAR"_"$NAME".json"
done
