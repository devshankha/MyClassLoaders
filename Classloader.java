

public synchronized Class loadClass(String className, boolean resolveIt) 
         throws ClassNotFoundException { 
         Class result; 
         byte classData[]; 
         System.out.println(" >>>>>> Load class : "+className); 
         /* Check our local cache of classes */ 
         result = (Class)classes.get(className); 
         if (result != null) { 
             System.out.println(" >>>>>> returning cached result."); 
             return result; 
} 

     /* Check with the primordial class loader */ 
try { 
    result = super.findSystemClass(className);
    if (className.startsWith("java.")) 
    throw newClassNotFoundException();  
    System.out.println(" >>>>>> returning system class (in CLASSPATH)."); 
    return result; 
    } catch (ClassNotFoundException e) { 
             System.out.println("        >>>>>> Not a system class."); 
    } 
    /* Try to load it from our repository */ 
classData = getClassImplFromDataBase(className); 
if (classData == null) { 
    throw new ClassNotFoundException(); 
} 
  /* Define it (parse the class file) */ 
result = defineClass(classData, 0, classData.length); 
if (resolveIt) { 
    resolveClass(result); 
} 
classes.put(className, result); 
System.out.println("        >>>>>> Returning newly loaded class."); 
        return result; 
} 
