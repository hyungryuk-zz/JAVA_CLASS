package refactoring.typecodewithClass;

public class Main {
    public static void main(String[] args) {
        Item book = new Item(
            Item.TYPECODE_BOOK,
            "������ ����",
            4800);

        Item dvd = new Item(
            Item.TYPECODE_DVD,
            "������ ��, Ư����",
            3000);

        Item soft = new Item(
            Item.TYPECODE_SOFTWARE,
            "Ʃ���ӽ� ���ķ�����",
            3200);

        System.out.println("book = " + book.toString());
        System.out.println("dvd  = " + dvd.toString());
        System.out.println("soft = " + soft.toString());
    }
}
