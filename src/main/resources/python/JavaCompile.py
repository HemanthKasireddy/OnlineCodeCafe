import subprocess
import sys
def compile_java(java_file):
    print ("before code compile ")
    cmd = 'javac ' + java_file
    p = subprocess.Popen(cmd,shell=True,  stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    out, err = p.communicate()
	
compile_java(sys.argv[1])
