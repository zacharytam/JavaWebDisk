package com.example.demo.collection;

import com.example.model.User;

import java.util.*;

/**
 * Created by cuizhixiang on 2017/6/22.
 **/
public class TestList {
    public static void main(String agrs[]){
        TestList.test3();
    }
    public static void test(){
        List<User> list = new ArrayList<User>();
        User u = new User("螃蟹","1478945648",45);
        list.add(u);
        list.add(new User("海星","15656985962",21));
        list.add(new User("海星","15656985962",21));
        list.add(new User("海蜇","15656985962",27));
        list.add(1,new User("水母","15648975898",23));
        list.add(u);
        for(User user:list){
            System.out.println(user);
        }
        User user = new User("龙虾","1478945648",45);
        list.set(2,user);
        System.out.println(list.get(1));
        System.out.println(list.indexOf(u));
        System.out.println(list.lastIndexOf(u));
        System.out.println("*********************************");
        System.out.println("subList:"+list.subList(0,3));
        User user1 = new User("沙丁鱼","15648956",56);
        ListIterator<User> listIterator = list.listIterator();
        listIterator.add(user1);
        while(listIterator.hasNext()){
            System.out.println(listIterator.nextIndex());
            System.out.println(listIterator.next());
            listIterator.remove();
        }
        System.out.println("*********************************");
        ListIterator<User> listIterator1 = list.listIterator(list.size());
        while (listIterator1.hasPrevious()){
            if(listIterator.previousIndex() == 0)break;
            System.out.println(listIterator.previous());
            System.out.println(listIterator.previousIndex());
        }
    }

    public static void test1(){
        LinkedList<User> list = new LinkedList<User>();
        User u = new User("螃蟹","1478945648",45);
        list.add(u);
        list.add(new User("海星","15656985962",21));
        list.add(new User("海星","15656985962",21));
        list.add(new User("海蜇","15656985962",27));
        list.add(1,new User("水母","15648975898",23));
        list.add(u);
        // 添加到列表开头
        list.addFirst(new User("蓝鳍金枪鱼","16598794856",58));
        // 添加到列表结尾
        list.addLast(new User("鼠鲨","154548956",100));
        ListIterator<User> iterator = list.listIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("*****************");
        // 逆序输出
        Iterator<User> iterator1 = list.descendingIterator();
        while(iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
        System.out.println("*****************");
        System.out.println(list.element());
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        list.offer(new User("象拔蚌","41564996",89));
        System.out.println(list.getLast());
        list.offerFirst(new User("寄居蟹","456846456",59));
        list.offerLast(new User("面包蟹","56459648",62));
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println("*****************");
        list.push(new User("鲍鱼","456856456",68));
        System.out.println(list.peek());
        list.pop();
        System.out.println(list.peek());
        System.out.println(list.poll());
    }

    public static void test3(){

        long time3 = new Date().getTime();
        LinkedList linkedlist = new LinkedList();
        for(int m=0;m<10000000;m++){
            linkedlist.push(m);      //当在200000条数据之前插入20000条数据时，LinkedList只用了1125多ms.这就是LinkedList的优势所在
            linkedlist.pop();
            linkedlist.poll();
        }
        long time4 = new Date().getTime();
        System.out.println("LinkedList:"+(time4 - time3));

        long time5 = new Date().getTime();
        ArrayList arrayList = new ArrayList();
        for(int n=0;n<1000000;n++){
            arrayList.add(n);  //当在200000条数据之前插入20000条数据时，ArrayList用了18375多ms.时间花费是arraylist的近20倍(视测试时机器性能)
            arrayList.get(n);
        }
        Iterator iterator = arrayList.iterator();
        while(iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
        long time6 = new Date().getTime();
        System.out.println("ArrayList"+(time6 - time5));
    }
}
