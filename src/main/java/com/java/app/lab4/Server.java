package com.java.app.lab4;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] arg) {//объявление объекта класса ServerSocket
        ServerSocket serverSocket = null;
        Socket clientAccepted = null;//объявление объекта класса Socket
        ObjectInputStream sois = null;//объявление байтового потока ввода
        ObjectOutputStream soos = null;//объявление байтового потока вывода
        try {
            System.out.println("server starting....");
            serverSocket = new ServerSocket(2525);//создание сокета сервера для
//заданного порта
            clientAccepted = serverSocket.accept();//выполнение метода, который
//обеспечивает реальное подключение сервера к клиенту
            System.out.println("connection established....");
//создание потока ввода soos = new
            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());//создание потока
//вывода
            String clientMessageRecieved = (String) sois.readObject();//объявление
//строки и присваивание ей данных потока ввода, представленных
//в виде строки (передано клиентом)
            while (!clientMessageRecieved.equals("quite"))//выполнение цикла: пока
//строка не будет равна «quite»
            {
                System.out.println("message recieved: '" + clientMessageRecieved + "'");
                Service service = new Service();
                clientMessageRecieved = service.findMatrix(clientMessageRecieved);
                soos.writeObject(clientMessageRecieved);//потоку вывода
//присваивается значение строковой переменной (передается клиенту)

                clientMessageRecieved = (String) sois.readObject();//строке
//присваиваются данные потока ввода, представленные в виде строки
//(передано клиентом)
            }
        } catch (Exception e) {
        } finally {
            try {
                sois.close();//закрытие потока ввода
                soos.close();//закрытие потока вывода
                clientAccepted.close();//закрытие сокета, выделенного для клиента
                serverSocket.close();//закрытие сокета сервера
            } catch (Exception e) {
                e.printStackTrace();//вызывается метод исключения е
            }
        }
    }
}

