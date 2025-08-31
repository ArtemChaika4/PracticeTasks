package edu.dnu.fpm.pz;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("<ArrayList>");
        try{
            demo(new ArrayList<Integer>());
        }
        catch (MyException exception){
            exception.toLog();
        }

        System.out.println("\n<LinkedList>");
        try{
            demo(new LinkedList<Integer>());
        }
        catch (MyException exception){
            exception.toLog();
        }
    }

    public static void demo(IList<Integer> list) throws MyException {
        list.addLast(2);
        list.addFirst(100);
        list.add(0, 0);
        list.add(1, 1);

        System.out.print("List: ");
        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + " ");
        }

        System.out.println("\nRemoved 2-nd element: " + list.remove(2));
        list.replace(2, -10);
        list.addLast(10);
        list.addFirst(-1);

        System.out.print("List: ");
        for (int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + " ");
        }

        int size = list.size();
        for(int i = 0; i < size; i++){
            list.remove(0);
        }

        System.out.println("\nList is empty: " + list.isEmpty());
        list.remove(1);
    }
}
