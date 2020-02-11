import java.lang.reflect.*;
public class Main {

    public static void main(String args[]){

        /**
         * Getting basic class informations with reflection class
         */

        // Retrieving Class Object
        Class myClass = Car.class;

        // To get class name
        System.out.println("Class Name : "+myClass.getName());

        // To get class modifier
        int modifier = myClass.getModifiers();

        // Checking class modifier
        if(Modifier.isPublic(modifier)){
            System.out.println("Public Modifier");
        } else if (Modifier.isAbstract(modifier)){
            System.out.println("Abstract Modifier");
        } else if (Modifier.isPrivate(modifier)){
            System.out.println("Private Modifier");
        } else if (Modifier.isFinal(modifier)){
            System.out.println("Final Modifier");
        }

        // Checking class is interface or not
        System.out.println("is interface : "+myClass.isInterface());

        // Getting all the interfaces that this class implements
        Class[] interfaces = myClass.getInterfaces();

        // Iterating in interfaces that implemented by class
        for(Class inter:interfaces){
            System.out.println("Interface Name : "+inter.getName());
        }

        // Getting super class of the class that it extends
        Class superClass = myClass.getSuperclass();

        // Getting super class name
        System.out.println("Supper Class Name : "+ superClass.getName());

        /**
         * Getting information abot class members.
         */

        // it gets all the public fields inside the class
        Field[] fields = myClass.getFields();

        // iterating in the public fields
        for( Field field : fields){
            System.out.println("Field : " + field.getName() );
        }

        // get all declared fields
        Field[] declaredFields = myClass.getDeclaredFields();

        // iterating in the all fields and their types
        // not : it only gives field that are declared in class not in its super class.
        for(Field field : declaredFields){
            System.out.println("Declared Field : "+ field.getName());
            System.out.println("Declared Field Type : "+ field.getType().getName());
        }

        System.out.println(" ---------- Constructors  ---------");

        // get all the public constructor
        Constructor[] constructors = myClass.getConstructors();

        // iterating in the all public constructors
        for(Constructor constructor: constructors){
            System.out.println("Constructor(");
            // get all paremeter type of the constructor
            Class[] parameters = constructor.getParameterTypes();
            for(Class parameter:parameters){
                System.out.println(parameter.getName()+" ");
            }
            System.out.println(")");
        }
        System.out.println(" ---------- Public Methods  ---------");

        // get all public methods
        Method[] methods = myClass.getMethods();

        // iterating in the all public methods
        for(Method method: methods){
            // get method name
            System.out.println("Method name : "+method.getName());
            // get method return type
            System.out.println("Method return type : "+method.getReturnType());
            // get parameters of the method
            Class[] methodParameters = method.getParameterTypes();

            // iterating in the parameters of the method
            for(Class parameter : methodParameters){
                // getting parameter's name
                System.out.println("parameter : "+parameter.getName() + " ");
            }

        }

        System.out.println(" ---------- Declared Methods  ---------");

        // get all  methods
        Method[] declaredMethods = myClass.getMethods();

        // iterating in the all methods
        for(Method method: declaredMethods){
            // get method name
            System.out.println("Method name : "+method.getName());
            // get method return type
            System.out.println("Method return type : "+method.getReturnType());
            // get parameters of the method
            Class[] methodParameters = method.getParameterTypes();

            // iterating in the parameters of the method
            for(Class parameter : methodParameters){
                // getting parameter's name
                System.out.println("parameter : "+parameter.getName() + " ");
            }

        }

    }
}
