import java.util.Scanner;
import java.util.Arrays;
class StringProcessor{
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        String s1=input.nextLine();
        reverseString(s1);
        System.out.println(countOccurences("hellohowarehowrtehowfdghow","how"));
        splitAndCapitalize("Welcome to Samr");

    }
    public static void reverseString(String str){
        String rev="";
        for(int i=str.length()-1;i>=0;i--){
            rev=rev+str.charAt(i);
        }
        System.out.print(rev);
    }
    
    public static int countOccurences(String text,String sub){
        int count=0;
        int index=0;
        while(text.length()>sub.length()){
            
        if(text.contains(sub)){
            count++;
            index=text.indexOf(sub);
            //System.out.println(index);
            text=text.substring(index+sub.length(), text.length());
            //System.out.println(text);
        }
        }
        return count;
    }
    public static void splitAndCapitalize(String str){
        String[] arr1=str.split(" ");
        String newStr="";
        //System.out.println(Arrays.toString(arr1));
        for(int i=0;i<arr1.length;i++){
            arr1[i]=arr1[i].toUpperCase();
        }
        for(int i=0;i<arr1.length;i++){
            newStr=newStr+arr1[i] +" ";
        }
        
        System.out.println(newStr);

    }

}