import java.awt.*;
import javax.swing.*;
import java.lang.reflect.Field;

@SuppressWarnings("serial")
public class DrawingComponent extends JPanel {
    @Override
    protected void paintComponent(Graphics gh) {
        Graphics2D drp = (Graphics2D)gh;
        long max = Model.y[0][0];
        for(int i=0; i<2; i++)
            for(int j=0; j<5; j++)
                if(Model.y[i][j]>max)
                    max=Model.y[i][j];
        long dy = max/9;
        //горизонтальные линии и обозначения
        for (int i=2; i<11; i++) {
            drp.drawLine(75, 50+44*i, 550, 50+44*i);
            long vs = max - (i-2)*dy;
            drp.drawString(vs+"", 10, 50+44*i);
        }
        drp.drawLine(75, 50+44*11, 550, 50+44*11);
        drp.drawString(0+"", 10, 50+44*11);
        drp.drawString("LinkedList", 100, 40);
        drp.drawString("ArrayList", 100, 60);

        drp.drawString("10", 70, 550);
        drp.drawString("100", 170, 550);
        drp.drawString("1000", 270, 550);
        drp.drawString("10000", 370, 550);
        drp.drawString("100000", 470, 550);

        drp.setColor(Color.BLUE);
        drp.fillRect(80, 30, 10, 10);
        drp.setColor(Color.RED);
        drp.fillRect(80, 50, 10, 10);

        for (int i=0; i<5; i++) {
            //строим саму гистограмму
            //извлекаем цвет для каждого графика
            Color color = Color.RED;
            for (int j=0;j<2;j++) {
                try {
                    Field field = Class.forName("java.awt.Color").getField(Model.col[j].toLowerCase());
                    color = (Color)field.get(null);
                }
                catch (Exception e) {}
                drp.setColor(color);
                //переводим полученные данные в реальные координаты
                int realY = (int) (534-44*Model.y[j][i]/dy);
                drp.fillRect(75+20*j+100*i, realY, 20,(int) (Model.y[j][i]*44/dy));
            }
        }
    }
}