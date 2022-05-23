package companies;

import java.util.*;

enum MenuType {
        Entry, Option, Category;
    };

    class Menu {
        String id;
        String name;
        MenuType type;
    }

    class Entry extends Menu {
        float price;
        List<String> relatedIds;

        public Entry(MenuType type, String name, String id, float price, List<String> ids) {
            this.type = type;
            this.name = name;
            this.id = id;
            this.price = price;
            this.relatedIds = ids;
        }
    }

    class Option extends Menu {
        float price;

        public Option(MenuType type, String name, String id, float price) {
            this.type = type;
            this.name = name;
            this.id = id;
            this.price = price;
        }
    }

    class Category extends Menu {
        List<String> relatedIds;

        public Category(MenuType type, String name, String id, List<String> ids) {
            this.type = type;
            this.name = name;
            this.id = id;
            this.relatedIds = ids;
        }
    }

    class MenuStream {
        List<String> list = Arrays.asList("1", "ENTREE", "Spaghetti", "10.95", "2", "3", "5", " ", "7", "OPTION",
                "meetball", "10.1", "   ");
        int index = 0;

        public String nextLine() {
            return list.get(index++);
        }
    }

    class MenuHelper {
        LinkedHashMap<String, Menu> map = new LinkedHashMap<>();

        Menu BuildMenuFromStream(List<String> input) {
            String id = input.get(0);
            MenuType whichType = MenuType.valueOf(input.get(1));
            String name = input.get(2);

            Menu res = null;
            float price;
            switch (whichType) {
            case Entry:
                price = Float.valueOf(input.get(3));

                res = new Entry(whichType, name, id, price, input.subList(4, input.size()));
                break;
            case Option:
                price = Float.valueOf(input.get(3));
                res = new Option(whichType, name, id, price);
                break;
            case Category:
                res = new Category(whichType, name, id, input.subList(3, input.size()));
                break;
            default:
                // TODO: throw exceptions
                break;
            }

            return res;
        }

        public MenuHelper(MenuStream stream) {
            
            String str = null;
            List<String> temp = new ArrayList<>();
            while( (str = stream.nextLine()) != null ) {
              if (str.isEmpty()) {
                Menu cur = BuildMenuFromStream(temp);
                map.put(cur.id, cur);
                temp.clear();
              } else temp.add(str);
          }
       }
    }

public class CloudChief {

    public static void main(String[] args) {
        MenuHelper helper = new MenuHelper(new MenuStream());
        System.out.println(helper.map);
    }
}
