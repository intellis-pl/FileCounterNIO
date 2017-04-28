This algorithm counts XML files for all tree branches with NIO library. 

For example, we have such a tree directories with subdirectories as below:

 

A-|

    |-B we.xml, 3d.xml

    |-C da.txt, rt.xml

    |-D ooo.xml

        |-F  mama.mov , figo.flv

        |-E  tata.xml, mój.mp3, da.xml

 

Expected result of this algorithm is to count any files nested in subdirectories: 
A 6, B 2, C 1, D 3, F 0, E 2
All files: 6