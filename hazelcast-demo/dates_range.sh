#!/bin/bash
dates=()
for (( date=date; date != `date  -d'10 days ago' +'%Y.%m.%d'`; )); do
    dates+=( "$date" )
    date="$(date --date="$date + 1 days" +'%Y%m%d')"
done
echo "${dates[@]}"