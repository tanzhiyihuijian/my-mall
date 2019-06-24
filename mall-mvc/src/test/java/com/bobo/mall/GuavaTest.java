package com.bobo.mall;

import com.bobo.mall.api.entity.User;
import com.google.common.base.*;
import com.google.common.collect.*;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuavaTest {

    @Test
    public void testStrOpt() {

        // 判断字符串

        String input = "";

        // 1. 判断非空
        boolean flag = Strings.isNullOrEmpty(input);
        System.out.println(flag);


        // 2. 判断两个字符串相同的前缀或者后缀
        String a = "com.bobo.mall";
        String b = "com.bobo.mall-api";
        String c = "net.com.bobo.mall";

        System.out.println("a, b common prefix: " + Strings.commonPrefix(a, b));
        System.out.println("b, c common prefix: " + Strings.commonPrefix(b, c));
        System.out.println("a, c common suffix: " + Strings.commonSuffix(a, c));
        System.out.println("b, c common suffix: " + Strings.commonSuffix(b, c));


        // 3. 补全字符串
        String s = Strings.padStart("123", 6, '0');
        System.out.println("padStart: " + s);

        s = Strings.padEnd("123", 5, 'x');
        System.out.println("padEnd: " + s);


        /**
          4. 拆分字符串
            onPattern(): 参数接受一个正则表达式
            trimResults(): 表示要对结果做 trim
            omitEmptyStrings(): 表示忽略空字符
            split(): 执行拆分操作
         */
        Iterable<String> splitResult = Splitter.onPattern("[,，]{1,}")
                .trimResults()
                .omitEmptyStrings()
                .split("hello,, world, 世界，   水平   ");

        for (String item : splitResult) {
            System.out.print(item + " ");
        }
        System.out.println();

        // 二次拆分: 首先使用 onPattern做第一次拆分, 然后再通过 withKeyValueSeparator()方法做第二次拆分
        String toSplitString = "a=b;c=d,e=f";
        Map<String, String> map = Splitter.onPattern("[;,]{1,}").withKeyValueSeparator('=').split(toSplitString);
        System.out.println(map);


        // 5. 合并字符串
        String joinResult = Joiner.on(" ").join(new String[]{"hello", "world"});
        System.out.println("joinResult: " + joinResult);

        // Splitter可以对字符串做二次的拆分, 对应的, Joiner也可以逆向操作
        Map<String, String> strMap = new HashMap<>();
        strMap.put("a", "b");
        strMap.put("c", "d");

        // 使用 withKeyValueSeparator() 可以对 map做合并, 合并的结果是 a=b, c=d
        String joinStr = Joiner.on(", ").withKeyValueSeparator("=").join(strMap);
        System.out.println("joinStr: " + joinStr);


    }


    @Test
    public void testObj() {

        // guava操作对象

        // 通过 Objects.equals() 来避免空指针
        boolean equal = Objects.equal(new User(), new User());
        System.out.println("equal: " + equal);

        Preconditions.checkNotNull("name", "name may not null");

    }


    @Test
    public void testOptional() {

        // Guava库中使用了 Optional接口来使 null快速失败, 即在可能为 null的对象上做了一层封装, 在使用静态方法 of()时
        // 如果传入的对象为空, 就抛出 NullPointException

        // Guava
        Optional<Integer> op = Optional.of(123);
        Integer i = op.get();
        System.out.println(i);

        // jdk
        java.util.Optional<User> op2 = java.util.Optional.of(new User().setAge(10));
        User user = op2.get();
        System.out.println("user: " + user);

    }


    @Test
    public void testCollection() {

        // 使用 guava的不可变集合


        // 1. 使用 builder创建不可变集合
        ImmutableSet<String> set = ImmutableSet.<String>builder()
                .add("red", "green", "white", "black", "grey")
                .build();
        set.forEach(System.out::print);
        System.out.println();


        // 2. 使用 of()静态方法创建
        ImmutableSet<Integer> numSet = ImmutableSet.of(1, 2, 3, 4, 5);
        numSet.forEach(System.out::print);
        System.out.println();


        // 3. 使用 copyOf() 静态方法创建
        ImmutableSet<Integer> copySet = ImmutableSet.copyOf(new Integer[] {33, 24, 15});
        copySet.forEach(System.out::print);
        System.out.println();


        // Multiset
        // Multiset看似是一个 Set, 但是实质上不是一个 Set, 它没有继承 Set接口, 它继承的是 Collection<E>接口
        // 你可以向 Multiset中添加重复的元素, Multiset会对添加的元素做一个计数
        HashMultiset<Object> multiset = HashMultiset.create();
        String sentences = "this is a story, there is a good girl in the story!";
        Iterable<String> words = Splitter.onPattern("[^a-z]{1,}").omitEmptyStrings().trimResults().split(sentences);
        for (String word : words) {
            System.out.print(word + " ");
            multiset.add(word);
        }
        System.out.println();

        for (Object element : multiset.elementSet()) {
            System.out.println(element + " : " + multiset.count(element));
        }

    }


    @Test
    public void testBiMap() {

        // 我们知道 map是一种键值对映射, 这个映射是键到值的映射
        // 而 BiMap首先也是一种 map, 它的特别之处在于, 既提供键到值的映射, 也提供值到键的映射, 所以它是双向 map
        BiMap<String, String> weekNameMap = HashBiMap.create();

        weekNameMap.put("星期一", "Monday");
        weekNameMap.put("星期二", "Tuesday");
        weekNameMap.put("星期三", "Wednesday");
        weekNameMap.put("星期四", "Thursday");
        weekNameMap.put("星期五", "Friday");
        weekNameMap.put("星期六", "Saturday");
        weekNameMap.put("星期日", "Sunday");

        String weekName = weekNameMap.getOrDefault("星期x", "11");
        System.out.println(weekName);

        String monday = weekNameMap.inverse().get("Monday");
        System.out.println(monday);

    }


    @Test
    public void testMultiMap() {

        // 一键多值的 map
        // 有时候我们需要这样的数据类型 Map<String, Collection<String>>, guava中的 Multimap就是为了解决这类问题的

        Multimap<String, String> multimap = ArrayListMultimap.create();

        multimap.put("Fruits", "Banana");
        multimap.put("Fruits", "Apple");
        multimap.put("Fruits", "Pear");

        multimap.put("Vegetables", "Carrot");

        int size = multimap.size();
        System.out.println("size: " + size);

        Collection<String> fruits = multimap.get("Fruits");
        System.out.println(fruits);

        // 遍历
        for (String s : multimap.values()) {
            System.out.print(s + " ");
        }
        System.out.println();


        // remove a single value
        multimap.remove("Fruits", "Pear");
        System.out.println(multimap);

        // remove all value for a key
        multimap.removeAll("Fruits");
        System.out.println(multimap);

    }


    @Test
    public void testTable() {

        // guava中提供了一种二维表结构 Table, 使用 Table可以实现二维矩阵的数组结构

        HashBasedTable<Integer, Integer, String> table = HashBasedTable.create();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 5; col++) {
                table.put(row, col, "value of cell (" + row + ", " + col + ")");
            }
        }

        for (int row = 0; row < table.rowMap().size(); row++) {
            Map<Integer, String> rowData = table.row(row);
            for (int col = 0; col < rowData.size(); col++) {
                System.out.println("cell(" + row + ", " + col + ") value is : " + rowData.get(col));
            }
        }
    }


    @Test
    public void testIterator() {

        List<String> fruits = Lists.newArrayList("Apple", "Pear", "Peach", "Banana");
        Predicate<String> condition = input -> input != null && input.startsWith("P");


        // all(): 判断
        boolean allIsStartWithP = Iterators.all(fruits.iterator(), condition);
        System.out.println("allIsStartWithP: " + allIsStartWithP);


    }



}
