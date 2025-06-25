
import java.util.*;
import java.io.*;

public class PackerUnpackerTool {

    public static void main(String[] args) throws Exception {
        Scanner sobj = new Scanner(System.in);

        System.out.println("-----------------------------------------------------");
        System.out.println("------- Marvellous Packer Unpacker CUI Module -------");
        System.out.println("-----------------------------------------------------");

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = sobj.nextInt();

            switch (choice) {
                case 1:
                    Packerfinal.main(null);
                    break;
                case 2:
                    Unpackerfinal.main(null);
                    break;
                case 3:
                    System.out.println("Thank you for using Marvellous Packer Unpacker tool");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Packing");
        System.out.println("2. Unpacking");
        System.out.println("3. Exit");
    }
}

class Packerfinal {
    public static void main(String[] args) throws Exception {
        Scanner spobj = new Scanner(System.in);

        System.out.println("----------------- Packing Activity ------------------
");

        System.out.print("Enter the name of Directory to pack: ");
        String FolderName = spobj.nextLine();
        File fpobj = new File(FolderName);

        System.out.print("Enter the name of packed file to create: ");
        String PackedFile = spobj.nextLine();
        File Packobj = new File(PackedFile);

        if (!Packobj.createNewFile()) {
            System.out.println("Unable to create packed file");
            return;
        }

        FileOutputStream foobj = new FileOutputStream(Packobj);
        if (fpobj.exists()) {
            File[] files = fpobj.listFiles();
            int packedCount = 0;

            for (File file : files) {
                if (file.getName().endsWith(".txt")) {
                    String header = file.getName() + " " + file.length();
                    header = String.format("%-100s", header);

                    foobj.write(header.getBytes());

                    FileInputStream fiobj = new FileInputStream(file);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fiobj.read(buffer)) != -1) {
                        foobj.write(buffer, 0, bytesRead);
                    }
                    fiobj.close();
                    packedCount++;
                    System.out.println("File packed: " + file.getName());
                }
            }

            System.out.println("-----------------------------------------------------");
            System.out.println("Packing completed. Files packed: " + packedCount);
            System.out.println("-----------------------------------------------------");
        } else {
            System.out.println("Directory not found.");
        }
        foobj.close();
    }
}

class Unpackerfinal {
    public static void main(String[] args) throws Exception {
        Scanner suobj = new Scanner(System.in);

        System.out.println("---------------- Unpacking Activity -----------------
");

        System.out.print("Enter the name of the packed file to unpack: ");
        String PackedFile = suobj.nextLine();
        File fuobj = new File(PackedFile);

        if (!fuobj.exists()) {
            System.out.println("Packed file not found.");
            return;
        }

        FileInputStream fuiobj = new FileInputStream(fuobj);
        byte[] headerBuffer = new byte[100];
        int unpackedCount = 0;

        while (fuiobj.read(headerBuffer, 0, 100) > 0) {
            String header = new String(headerBuffer).trim();
            String[] tokens = header.split(" ");
            String fileName = tokens[0];
            int fileSize = Integer.parseInt(tokens[1]);

            File outFile = new File(fileName);
            outFile.createNewFile();

            byte[] fileBuffer = new byte[fileSize];
            fuiobj.read(fileBuffer, 0, fileSize);

            FileOutputStream outStream = new FileOutputStream(outFile);
            outStream.write(fileBuffer);
            outStream.close();

            System.out.println("File unpacked: " + fileName);
            unpackedCount++;
        }

        fuiobj.close();
        System.out.println("-----------------------------------------------------");
        System.out.println("Unpacking completed. Files unpacked: " + unpackedCount);
        System.out.println("-----------------------------------------------------");
    }
}
