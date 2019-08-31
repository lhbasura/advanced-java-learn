package com.lhbasura.annotaion.demo;


import org.junit.Test;

@SourceAnnotation("hello,this is source Annotation")
class Source{

}

@ClassAnnotation("hello,this is class Annotation")
class Class{

}

@RuntimeAnnotation("hello,this is runtime Annotation")
class Runtime{

}
public class AnnotationTest {

    @Test
    public void sourceTest(){
        SourceAnnotation annotation=Source.class.getAnnotation(SourceAnnotation.class);
        if(annotation!=null)
        {
            System.out.println(annotation.value());
        }else {
            System.out.println("No such annotation.");
        }
    }

    @Test
    public void classTest(){
        ClassAnnotation annotation=Class.class.getAnnotation(ClassAnnotation.class);
        if(annotation!=null)
        {
            System.out.println(annotation.value());
        }else {
            System.out.println("No such annotation.");
        }
    }

    @Test
    public void routimeTest(){
        RuntimeAnnotation annotation=Runtime.class.getAnnotation(RuntimeAnnotation.class);
        if(annotation!=null)
        {
            System.out.println(annotation.value());
        }else {
            System.out.println("No such annotation.");
        }
    }
}
