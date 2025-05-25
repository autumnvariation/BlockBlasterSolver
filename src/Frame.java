import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Frame extends JFrame {
    JPanel [][] panels = new JPanel[8][8];
    public Frame() {
        this.setSize(new Dimension(1080,1080));

        this.setLayout(new GridLayout(8,8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                panels[i][j] = new JPanel();
                panels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                panels[i][j].setOpaque(true);
                panels[i][j].setBackground(Color.white);
                panels[i][j].setVisible(true);
                this.add(panels[i][j]);
            }
        }
        repaint();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
    }
    public void setColors(int[][] state){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (state[i][j] == 0){
                    panels[i][j].setBackground(Color.white);
                }
                else if (state[i][j] == 1){
                    panels[i][j].setBackground(Color.blue);
                }
                else{
                    panels[i][j].setBackground(Color.green);
                }
            }
        }
    }
    public void addSolution(int[] solution){
        //solution defined by block1 + position, block2 + position, block3 + position
        //ex: 11,2,4, 23,4,6, 2,4,8
        for (JPanel[] panel : panels) {
            for (JPanel jPanel : panel) {
                jPanel.removeAll();
            }
        }
        for (int i = 0; i < Blocks.BLOCKS.get(solution[0]).length; i++) {
            for (int j = 0; j < Blocks.BLOCKS.get(solution[0])[0].length; j++) {
                if(Blocks.BLOCKS.get(solution[0])[i][j] != 0){
                    JPanel panel = panels[i + solution[1]][solution[2] + j];
                    panel.add(new JLabel("1", SwingConstants.CENTER), BorderLayout.CENTER);
                    panel.setBackground(Color.green);
                }
            }
        }

        for (int i = 0; i < Blocks.BLOCKS.get(solution[3]).length; i++) {
            for (int j = 0; j < Blocks.BLOCKS.get(solution[3])[0].length; j++) {
                if(Blocks.BLOCKS.get(solution[3])[i][j] != 0){
                    JPanel panel = panels[i + solution[4]][j + solution[5]];
                    panel.add(new JLabel("2", SwingConstants.CENTER), BorderLayout.CENTER);
                    panel.setBackground(Color.magenta);
                }
            }
        }
        for (int i = 0; i < Blocks.BLOCKS.get(solution[6]).length; i++) {
            for (int j = 0; j < Blocks.BLOCKS.get(solution[6])[0].length; j++) {
                if(Blocks.BLOCKS.get(solution[6])[i][j] != 0){
                    JPanel panel = panels[i + solution[7]][j + solution[8]];
                    panel.add(new JLabel("3", SwingConstants.CENTER), BorderLayout.CENTER);
                    panel.setBackground(Color.cyan);
                }
            }
        }
        revalidate();
        repaint();
    }
}
