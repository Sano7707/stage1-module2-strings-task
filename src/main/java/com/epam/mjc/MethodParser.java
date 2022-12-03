package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringTokenizer tokenizer = new StringTokenizer(signatureString, " ,(");
        String nextToken = tokenizer.nextToken();
        String methodName;
        String accessModifier;
        String rType;
        if(nextToken.equals("private") || nextToken.equals("public")){
            accessModifier = nextToken;
            rType = tokenizer.nextToken();
        }
        else{
            accessModifier = null;
            rType = nextToken;
        }
        methodName = tokenizer.nextToken();
        String methodSignature = signatureString.substring(signatureString.indexOf(methodName) + methodName.length());
        StringTokenizer tokenizer1 = new StringTokenizer(methodSignature, " ,)(");
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        List<String> list = new ArrayList<>();
        while (tokenizer1.hasMoreTokens()){
            list.add(tokenizer1.nextToken());
        }
        System.out.println(list);
        for (int i = 0; i < list.size() - 1; i+=2) {
            arguments.add(new MethodSignature.Argument(list.get(i),list.get(i+1)));
        }
        MethodSignature methodSignature1 = new MethodSignature(methodName,arguments);
        methodSignature1.setReturnType(rType);
        methodSignature1.setAccessModifier(accessModifier);

        return methodSignature1;
    }

    public static void main(String[] args) {
        MethodParser parser = new MethodParser();
        System.out.println(parser.parseFunction("private void log(String logString, LogLevel level, Context context)"));
    }
}
