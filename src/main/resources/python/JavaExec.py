import subprocess
import sys
def execute_java(java_file):
    print ("before code compile ")
    c,b=java_file.split(".")
    cmd1='java '+ c
    print (c)
    p1 = subprocess.Popen(cmd1,shell=True,  stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    out, err = p1.communicate()
    print(out)
    print(err)
	
execute_java(sys.argv[1])
