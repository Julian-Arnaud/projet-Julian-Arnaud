package fr.miage.GUI;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.Label;
import com.indvd00m.ascii.render.elements.Table;

public class Display {

    public static void Display(String[] curTab) {
        //call asciiRender
        IRender render = new Render();
        IContextBuilder builder = render.newBuilder();
        builder.width(40).height(20);
        Table table = new Table(9,9);
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                table.setElement(i+1,j+1,new Label(curTab[9*i + j]));
            }
        }
        builder.element(table);
        ICanvas canvas = render.render(builder.build());
        System.out.println(canvas.getText());
    }
}
