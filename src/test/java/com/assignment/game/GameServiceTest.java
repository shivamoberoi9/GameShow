package com.assignment.game;

import com.assignment.game.io.IOUtilImpl;
import com.assignment.game.model.Result;
import com.assignment.game.service.GameServiceImpl;
import com.assignment.game.util.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Utils.class})
public class GameServiceTest {

    private final int boxes = 3;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @InjectMocks
    GameServiceImpl gameService;

    @Mock
    IOUtilImpl ioUtil;

    @Test
    public void won_with_switching() {
        mockStatic(Utils.class);
        // Prize box is 2
        when(Utils.getRandomNumber(1, boxes)).thenReturn(2);
        //User selects box 1
        when(ioUtil.getUserInput()).thenReturn(1);
        // get the empty box as 3rd box
        PowerMockito.when(Utils.getDifferentNumber(1, 2, 1, boxes)).thenCallRealMethod();
        when(ioUtil.getUserInputForSwitchingTheOption()).thenReturn(true);
        // After switching
        PowerMockito.when(Utils.getDifferentNumber(1, 3, 1, boxes)).thenCallRealMethod();
        gameService.run(1, boxes);
        Assert.assertTrue(outContent.toString().contains(Result.WON.name()));

    }

    @Test
    public void won_without_switching() {
        mockStatic(Utils.class);
        // Prize box is 2
        when(Utils.getRandomNumber(1, boxes)).thenReturn(2);
        //User selects box 2
        when(ioUtil.getUserInput()).thenReturn(2);
        // get the empty box as 3rd box or 1st
        when(Utils.getDifferentNumber(2, 2, 1, boxes)).thenCallRealMethod();
        gameService.run(1, boxes);
        Assert.assertTrue(outContent.toString().contains(Result.WON.name()));

    }

    @Test
    public void lost_with_switching() {
        mockStatic(Utils.class);
        // Prize box is 2
        when(Utils.getRandomNumber(1, boxes)).thenReturn(2);
        //User selects box 2
        when(ioUtil.getUserInput()).thenReturn(2);
        // get the empty box as 3rd box or 1st box
        when(Utils.getDifferentNumber(2, 2, 1, boxes)).thenCallRealMethod();
        when(ioUtil.getUserInputForSwitchingTheOption()).thenReturn(true);
        // After switching
        when(Utils.getDifferentNumber(2, 3, 1, boxes)).thenCallRealMethod();
        when(Utils.getDifferentNumber(2, 1, 1, boxes)).thenCallRealMethod();
        gameService.run(1, boxes);
        Assert.assertTrue(outContent.toString().contains(Result.LOST.name()));
    }

    @Test
    public void lost_without_switching() {
        mockStatic(Utils.class);
        // Prize box is 2
        when(Utils.getRandomNumber(1, boxes)).thenReturn(2);
        //User selects box 1
        when(ioUtil.getUserInput()).thenReturn(1);
        // get the empty box as 3rd box
        when(Utils.getDifferentNumber(2, 1, 1, boxes)).thenCallRealMethod();
        gameService.run(1, boxes);
        Assert.assertTrue(outContent.toString().contains(Result.LOST.name()));
    }
}
