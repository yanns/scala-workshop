package section6

import javax.swing.{JPanel, JButton, JFrame}
import java.awt.event.{ActionEvent, ActionListener, WindowEvent, WindowAdapter}
import scala.language.implicitConversions

object ClickMeApplication extends App {

  // TODO: implement an implicit conversion so that
  // the commented code can compile and work as expected
  implicit def toActionListener(f: ActionEvent => Unit): ActionListener =
    new ActionListener {
      override def actionPerformed(event: ActionEvent): Unit = f(event)
    }

  new JFrame("click Me!") {
    val exitListener = new WindowAdapter {
      override def windowClosing(event: WindowEvent){
        System.exit(0)
      }
    }
    addWindowListener(exitListener)

    val button = new JButton("click me")
    button.addActionListener(new ActionListener {
      def actionPerformed(event: ActionEvent) {
        println("button clicked in a java way")
      }
    })
    // to un-comment

    button.addActionListener { event: ActionEvent =>
      println("button clicked in a scala way")
    }
    val f: ActionEvent => Unit = { event: ActionEvent =>
      println("button clicked in a scala way")
    }
    button.addActionListener(f)
    val panel = new JPanel()
    panel.add(button)
    setContentPane(panel)
    setSize(200, 122)
    setVisible(true)
  }
}
