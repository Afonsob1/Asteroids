package asteroids.view.screens;

import asteroids.view.screens.ScreenView;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.times;

public class ScreenViewTest extends Assertions {

    ScreenView screenView ;
    Font font;
    @BeforeEach
    void prepareScreen(){
        screenView = Mockito.mock(ScreenView.class, Mockito.CALLS_REAL_METHODS);
        font = new Font(Font.MONOSPACED,Font.PLAIN, 4);
        screenView.setFont(font);
        Mockito.when(screenView.getSize()).thenReturn(new TerminalSize(50,50));
    }

    @Test
    void TestInitScreen() {
        try{
            //when
            screenView.initScreen();

            // then
            assertEquals(screenView.getFont(), font);
            assertNotNull(screenView.getGraphics());
            //assertTrue(true);
            // final
            screenView.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void refresh() throws IOException {
        //given
        Screen screen = Mockito.mock(Screen.class);
        Mockito.when(screenView.getScreen()).thenReturn(screen);

        // when
        screenView.refresh();

        //then
        Mockito.verify(screen, times(1)).refresh(Screen.RefreshType.AUTOMATIC);
    }

    @Test
    void close() throws IOException {
        //given
        Screen screen = Mockito.mock(Screen.class);
        Mockito.when(screenView.getScreen()).thenReturn(screen);

        // when
        screenView.close();

        //then
        Mockito.verify(screen, times(1)).close();
    }

    @Test
    void clear() {

        //given
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Screen screen = Mockito.mock(Screen.class);
        Mockito.when(screenView.getScreen()).thenReturn(screen);
        Mockito.when(screenView.getGraphics()).thenReturn(graphics);

        //Alterado do BeforeEach para aqui
        Mockito.when(screenView.getSize()).thenReturn(new TerminalSize(50,50));

        // when
        screenView.clear();

        //then
        Mockito.verify(graphics, times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, times(1)).fillRectangle(new TerminalPosition(0, 0),
                new TerminalSize(50,50),' ');
    }

}