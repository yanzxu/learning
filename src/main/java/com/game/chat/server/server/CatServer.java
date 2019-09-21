package com.game.chat.server.server;

import com.game.chat.server.function.CatBean;
import com.game.chat.server.function.ClientBean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class CatServer {
    private static ServerSocket ss;
    public static HashMap<String, ClientBean> onlines;

    static {
        try {
            ss = new ServerSocket(8520);
            onlines = new HashMap<String, ClientBean>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    class CatClientThread extends Thread {
        private Socket client;
        private CatBean bean;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;

        public CatClientThread(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ois = new ObjectInputStream(client.getInputStream());
                    bean = (CatBean) ois.readObject();

                    switch (bean.getType()) {
                        case 0: {
                            ClientBean cbean = new ClientBean();
                            cbean.setName(bean.getName());
                            cbean.setSocket(client);
                            onlines.put(bean.getName(), cbean);
                            CatBean serverBean = new CatBean();
                            serverBean.setType(0);
                            serverBean.setInfo(bean.getTimer() + "  "
                                    + bean.getName() + "������");
                            HashSet<String> set = new HashSet<String>();
                            set.addAll(onlines.keySet());
                            serverBean.setClients(set);
                            sendAll(serverBean);
                            break;
                        }
                        case -1: {
                            CatBean serverBean = new CatBean();
                            serverBean.setType(-1);

                            try {
                                oos = new ObjectOutputStream(
                                        client.getOutputStream());
                                oos.writeObject(serverBean);
                                oos.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            onlines.remove(bean.getName());

                            CatBean serverBean2 = new CatBean();
                            serverBean2.setInfo(bean.getTimer() + "  "
                                    + bean.getName() + " " + " ������");
                            serverBean2.setType(0);
                            HashSet<String> set = new HashSet<String>();
                            set.addAll(onlines.keySet());
                            serverBean2.setClients(set);

                            sendAll(serverBean2);
                            return;
                        }
                        case 1: {
                            CatBean serverBean = new CatBean();

                            serverBean.setType(1);
                            serverBean.setClients(bean.getClients());
                            serverBean.setInfo(bean.getInfo());
                            serverBean.setName(bean.getName());
                            serverBean.setTimer(bean.getTimer());
                            sendMessage(serverBean);
                            break;
                        }
                        case 2: {
                            CatBean serverBean = new CatBean();
                            String info = bean.getTimer() + "  " + bean.getName()
                                    + "���㴫���ļ�,�Ƿ���Ҫ����";

                            serverBean.setType(2);
                            serverBean.setClients(bean.getClients());
                            serverBean.setFileName(bean.getFileName());
                            serverBean.setSize(bean.getSize());
                            serverBean.setInfo(info);
                            serverBean.setName(bean.getName());
                            serverBean.setTimer(bean.getTimer());
                            sendMessage(serverBean);

                            break;
                        }
                        case 3: {
                            CatBean serverBean = new CatBean();

                            serverBean.setType(3);
                            serverBean.setClients(bean.getClients());
                            serverBean.setTo(bean.getTo());
                            serverBean.setFileName(bean.getFileName());
                            serverBean.setIp(bean.getIp());
                            serverBean.setPort(bean.getPort());
                            serverBean.setName(bean.getName());
                            serverBean.setTimer(bean.getTimer());
                            sendMessage(serverBean);
                            break;
                        }
                        case 4: {
                            CatBean serverBean = new CatBean();

                            serverBean.setType(4);
                            serverBean.setClients(bean.getClients());
                            serverBean.setTo(bean.getTo());
                            serverBean.setFileName(bean.getFileName());
                            serverBean.setInfo(bean.getInfo());
                            serverBean.setName(bean.getName());
                            serverBean.setTimer(bean.getTimer());
                            sendMessage(serverBean);

                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                close();
            }
        }

        private void sendMessage(CatBean serverBean) {
            Set<String> cbs = onlines.keySet();
            Iterator<String> it = cbs.iterator();
            HashSet<String> clients = serverBean.getClients();
            while (it.hasNext()) {
                String client = it.next();
                if (clients.contains(client)) {
                    Socket c = onlines.get(client).getSocket();
                    ObjectOutputStream oos;
                    try {
                        oos = new ObjectOutputStream(c.getOutputStream());
                        oos.writeObject(serverBean);
                        oos.flush();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        }

        public void sendAll(CatBean serverBean) {
            Collection<ClientBean> clients = onlines.values();
            Iterator<ClientBean> it = clients.iterator();
            ObjectOutputStream oos;
            while (it.hasNext()) {
                Socket c = it.next().getSocket();
                try {
                    oos = new ObjectOutputStream(c.getOutputStream());
                    oos.writeObject(serverBean);
                    oos.flush();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        private void close() {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void start() {
        try {
            while (true) {
                Socket client = ss.accept();
                new CatClientThread(client).start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CatServer().start();
    }

}
