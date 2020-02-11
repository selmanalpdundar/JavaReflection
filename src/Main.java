import java.lang.reflect.*;
public class Main {

    public static void main(String args[]){
        Class myClass = Car.class;

        System.out.println("Class Name : "+myClass.getName());

        int modifier = myClass.getModifiers();

        if(Modifier.isPublic(modifier)){
            System.out.println("Public Modifier");
        } else if (Modifier.isAbstract(modifier)){
            System.out.println("Abstract Modifier");
        } else if (Modifier.isPrivate(modifier)){
            System.out.println("Private Modifier");
        } else if (Modifier.isFinal(modifier)){
            System.out.println("Final Modifier");
        }

        System.out.println("is interface : "+myClass.isInterface());

        Class[] interfaces = myClass.getInterfaces();

        for(Class inter:interfaces){
            System.out.println("Interface Name : "+inter.getName());
        }
        Class superClass = myClass.getSuperclass();

        System.out.println("Supper Class Name : "+ superClass.getName());



    }
}
