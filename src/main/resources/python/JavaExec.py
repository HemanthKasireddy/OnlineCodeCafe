import subprocess
import sys
from subprocess import STDOUT,PIPE

def execute_java(java_file):
   # print ("before code compile ")
    c,b=java_file.split(".")
    cmd='java '+ c
   # print ("hi",c.decode("utf-8"))
    p1 = subprocess.Popen(cmd,shell=True,  stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    out, err = p1.communicate()
  #  print("".join(map(chr, out)))

    print(out.decode("utf-8"))
    print(err.decode("utf-8"))
##    proc = subprocess.Popen(cmd, shell=True, stdin=PIPE, stdout=PIPE, stderr=STDOUT)
##    stdout,stderr = proc.communicate()
##    print ('This was "', stdout, '"')
   # print("After Run")
	
execute_java(sys.argv[1])
