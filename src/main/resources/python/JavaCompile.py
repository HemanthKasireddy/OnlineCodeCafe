import subprocess
import sys
from subprocess import STDOUT,PIPE

def compile_java(java_file):
   # print ("before code compile ")
    print(java_file)
    cmd = 'javac ' + java_file
    proc = subprocess.Popen(cmd,shell=True,  stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    out, err = proc.communicate()
##    proc = subprocess.Popen(cmd, shell=True, stdin=PIPE, stdout=PIPE, stderr=STDOUT)
##    stdout,stderr = proc.communicate()
##    print ('This was "', stdout, '"')
    #print(type(out))
    
   # print("".join(map(chr, out)))
    print(out.decode("utf-8"))
    print(err.decode("utf-8"))
   # print("After compile")

compile_java(sys.argv[1])
