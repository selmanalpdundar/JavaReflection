import java.lang.reflect.*;
import java.util.LinkedList;

public class Main {

    public static void main(String args[]){

        /**
         *  Using reflection for Introspection
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


        // calling method with name and  parameter type
        /*
            To be sure it is exist we need to wrap it with try catch block to prevent future craching
            that way we can have a control of flow
         */

        // it will handled by No such method exception
        try {
            Method method = myClass.getMethod("seColor", int.class);
        } catch (NoSuchMethodException e){
            System.out.println("Method not found : "+e.toString());
        }

        // it will work normally because it exists
        try {
            Method method = myClass.getMethod("setColor", int.class);
            System.out.println(method.getName());
        } catch (NoSuchMethodException e){
            System.out.println("Method not found : "+e.toString());
        }

        /**
         * Explanation of Generic Types in Java Reflection
         *  Due to Java's erasure semantics, generic type information is not represented at run time
         *  for that reason when we call a generic class's method it will cause exception
         *  we can call generic class's method by object class then it will work normally
         *
         */

        LinkedList<String> list = new LinkedList<String>();
        Class listClass = list.getClass();

        // Broken way
        try{
            Method method = listClass.getMethod("add",String.class);

        } catch (NoSuchMethodException e){
            System.out.println("Method not found : "+ e.toString());
        }

        // Correct way
        try{
            Method method = listClass.getMethod("add",Object.class);
            System.out.println("Method Name : "+method.getName());
        } catch (NoSuchMethodException e){
            System.out.println("Method not found : "+ e.toString());
        }


        /**
         * Using reflection for Program Manipulation
         */

        // Creating new object
        // Car mycar = new Car();
        Class carClass = Car.class;

        try {
            Car car = (Car) carClass.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // Creating new object with constructor with arguments
        // Car car = new Car("Sedan",1000,1994,2,"2000");

        // Creating new object with reflection
        // Create argument type array to get correct constructor
        Class[] classArguments = new Class[]{String.class,int.class,int.class,int.class,String.class};
        // Prepare value of the arguments
        Object[] arguments = new Object[]{new String("Sedan"),new Integer(1000),new Integer(1994),new Integer(2), new String("2000")};

        /**
         * Java wants us to handle exception for that reason we need to get them inside try catch and handle exceptions
         */
        try {
            // get constructor with correct parameter types
            Constructor constructor = carClass.getConstructor(classArguments);
            try {
                // Get instance from constructor with arguments value
                Car car = (Car) constructor.newInstance(arguments);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // Getting Fields Value
        // car.model -> if it were public but we encapsulate them so car.getModel()
        // car.secondHand

        Class carClass2 = Car.class;
        Car  car = new Car();

        try {
            Field field = carClass2.getField("secondHand");
            boolean secondHand =  (boolean) field.get(car);
            System.out.println("is it second hand :  "+secondHand);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


        // Setting Field Value
        // car.secondHand = false;

        try {
            Class c = car.getClass();
            Field f = c.getField("secondHand");
            f.set(car,new Boolean(true));
            boolean secondHand =  (boolean) f.get(car);
            System.out.println("is it second hand :  "+secondHand);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }




        // Invoking Methods
        String string1 = "Hello";
        String string2 = "World";
        // String result  = string1.concat(string2);

        // Getting a class
         Class c = String.class;
         // Preparing method types
         Class[] paramtypes =  new Class[]{String.class};
         // Preparing method parameters
         Object[] argms =  new Object[]{string2};

        try {
            // Getting method
            Method concatMethod = c.getMethod("concat",paramtypes);
            // Invoking method with arguments
            String result = (String) concatMethod.invoke(string1,argms);
            System.out.println("Result  = "+result);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        /** Forbidden Operations by privacy rules;
         *  Changing a Final Field
         *  Reading or writing a private field
         *  Invoking a private method
         *
         *  Not: Request granted if no security manager, or if the existing security manager allows it
         */


        // Accessing Private Fields

        System.out.println(" ----------- Accessing Private Field Wrong Implementation --------------");

        // This is the wrong implementation to access private field
        Class classPrivate = Car.class;
        Field[] privateFields = classPrivate.getDeclaredFields();

        for(Field field: privateFields){
            try{
                if(!Modifier.isStatic(field.getModifiers())){
                    System.out.println("Name of Field : "+field.get(car));
                }
            } catch (Exception e){
                // it will throw error with modifier "private"
                System.out.println("Exception : "+e.toString());
            }

        }

        System.out.println("---------------- Accessing Private Field Correct Implementation -----------------");

        // This is the correct implementation to access private field
        Class classPrivate2 = Car.class;
        Field[] privateFields2 = classPrivate.getDeclaredFields();

        for(Field field: privateFields2){
            try{
                field.setAccessible(true);
                if(!Modifier.isStatic(field.getModifiers())){
                    System.out.println("Name of Field : "+field.get(car));
                }
            } catch (Exception e){
                // it will throw error with modifier "private"
                System.out.println("Exception : "+e.toString());
            }

        }

    }
}
