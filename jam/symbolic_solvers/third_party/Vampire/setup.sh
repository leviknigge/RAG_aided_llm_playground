mkdir bin 
wget https://github.com/vprover/vampire/releases/download/v4.8casc2023/vampire_z3_rel_static_casc2023_6749.zip
unzip vampire_z3_rel_static_*.zip
cd bin
ln -s vampire_z3_rel_static_casc2023_6749 vampire
cd ..
rm vampire_z3_rel_static_*.zip