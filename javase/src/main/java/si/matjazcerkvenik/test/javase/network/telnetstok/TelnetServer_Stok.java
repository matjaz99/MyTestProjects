package si.matjazcerkvenik.test.javase.network.telnetstok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TelnetServer_Stok implements Runnable {

 

  private ServerSocket serverSocket;

  int port;

 

  public TelnetServer_Stok(int aPort) throws Exception {

    port = aPort;

    serverSocket = new ServerSocket(port);

  }

 

  public void run() {

    System.out.println("Telnet Server is ready on port " + port);

 

    while (true) {

      Session worker;

      try {

        worker = new Session(serverSocket.accept());

        Thread t = new Thread(worker);

        t.start();

      } catch (IOException e) {

        System.out.println("Server: accept error - " + e.getMessage());

      }

    }

 

  }

 

  public static void main(String[] args) throws Exception {

    TelnetServer_Stok server = new TelnetServer_Stok(3000);

    server.run();

  }

 

  class Session implements Runnable {

 

    private Socket client;

    private BufferedReader in = null;

    private PrintWriter out = null;

    private boolean authenticated;

    private int cnt = 0;

    private boolean firstTime = true;

    private boolean running;

 

    Session(Socket aClient) {

      client = aClient;

    }

 

    public void run() {

      String request;

      try {

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);

        System.out.println("Connecting from ip: " + client.getInetAddress());

 

      } catch (IOException e) {

        System.out.println("Worker error: " + e.getMessage());

      }

      running = true;

 

      while (running) {

        try {

          if (firstTime) {

            welcome();

          }

          if (!authenticated) {

            login();

            continue;

          }

 

          request = in.readLine();

          out.println();

          parseCommand(request);

        } catch (IOException e) {

          out.println("Disconnected");

 

          System.out.println("AbortConnection: " + e.getMessage());

          abortConnection();

        }

      } // end while true

      abortConnection();

    }

 

    private void login() throws IOException {

      cnt++;

 

      out.print("Username: ");

      out.flush();

      String user = in.readLine().trim();

      out.print("\nPassword: ");

      out.flush();

      String password = in.readLine().trim();

      out.println();

      /** @todo connection to OpenMN, calling checkUser */

      // check user

      if (user.equals("admin") && password.equals("admin")) {

        authenticated = true;

        out.println("\nWelcome to SI3000 MNS\n");

      } else {

        out.println("Authorization failure");

        if (cnt >= 3) {

          running = false;

        }

      }

    }

 

    private void welcome() throws IOException {

      out.println("SI3000 MN CLI Server");

      out.println("press RETURN for continue");

      in.readLine();

      firstTime = false;

    }

 

    private void parseCommand(String command) throws IOException {

      // parse command

      if (command.length()==0) {

        // empty line

        return;

      }

      if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("bye")) {

        out.println("bye");

        running = false;

        return;

      }

      /** @todo switch for all commands */

      // test resposne

      out.println(" Respoanse for command /" + command + "/");

    }

 

 

    private void abortConnection() {

      try {

        Thread.sleep(50);

      } catch (InterruptedException e) {

      }

      try {

        if (in != null) {

          in.close();

        }

        if (out != null) {

          out.close();

        }

      } catch (IOException e) {

      }

      System.out.println("Abort connection");

    }

  }

}

