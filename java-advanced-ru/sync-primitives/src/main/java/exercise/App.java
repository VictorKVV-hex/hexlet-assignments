package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        SafetyList list = new SafetyList();

        ListThread thread1 = new ListThread(list);
        ListThread thread2 = new ListThread(list);


//        thread1.run();
        // Запускаем потоки
        thread1.start();
        thread2.start();

        // Дожидаемся окончания выполнения потоков
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Size: " + list.getSize());
        // END
    }
}

