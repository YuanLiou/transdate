package ray.yuanliu

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.PrintWriter
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.UIManager

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        JFrame.setDefaultLookAndFeelDecorated(true)
        setUI()
        var success = false
        val fileChooser = JFileChooser()
        fileChooser.currentDirectory = File(System.getProperty("user.home"))
        val myfiles = fileChooser.showOpenDialog(JFrame())
        val parent = JFrame()
        if (myfiles == JFileChooser.APPROVE_OPTION) {
            // User selects a file
            val selectedFile = fileChooser.selectedFile
            // Check whether it is txt file
            if (!selectedFile.name.endsWith(".txt")) {
                // 不是 txt 檔案
                JOptionPane.showMessageDialog(parent, "請選擇文字檔案")
                System.exit(0)
                return
            }
            try {
                val br = BufferedReader(FileReader(selectedFile))
                val sb = StringBuilder()
                //讀取一行
                var line = br.readLine()
                while (line != null) {
                    val tokens = line.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val minkuo = tokens[0].toInt()
                    val year = minkuo + 1911
                    val result = year.toString() + "-" + tokens[1] + "-" + tokens[2]
                    sb.append(result)
                    //換行
                    sb.append(System.lineSeparator())
                    //讓他讀取下一行
                    line = br.readLine()
                }
                val everything = sb.toString()
                println("done!")
                val out = PrintWriter("result.txt")
                out.print(everything)
                out.close()
                success = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        if (success) {
            JOptionPane.showMessageDialog(parent, "Done!")
        } else {
            JOptionPane.showMessageDialog(parent, "Failed, Try again.")
        }
        parent.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        System.exit(0)
    }

    // Use Nimbus UI
    private fun setUI() {
        try {
            for (info in UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus" == info.name) {
                    UIManager.setLookAndFeel(info.className)
                    break
                }
            }
        } catch (e: Exception) {
            // If Nimbus is not available, fall back to cross-platform
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName())
            } catch (e2: Exception) {
                e2.printStackTrace()
            }
        }
    }
}
