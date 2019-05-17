package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTSPrimary;
  private TorpedoStore mockTSSecondary;

  @BeforeEach
  public void init(){
    this.mockTSPrimary = mock(TorpedoStore.class);
    this.mockTSSecondary = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTSPrimary, mockTSSecondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    when(mockTSPrimary.fire(1)).thenReturn(true);
    when(mockTSPrimary.isEmpty()).thenReturn(false);
    when(mockTSSecondary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTSPrimary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTSPrimary.fire(1)).thenReturn(true);
    when(mockTSSecondary.fire(1)).thenReturn(true);
    when(mockTSPrimary.isEmpty()).thenReturn(false);
    when(mockTSSecondary.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockTSPrimary, times(1)).fire(1);
    verify(mockTSSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_PrimaryEmpty(){
    // Arrange
    when(mockTSPrimary.fire(1)).thenReturn(false);
    when(mockTSSecondary.fire(1)).thenReturn(true);
    when(mockTSPrimary.isEmpty()).thenReturn(true);
    when(mockTSSecondary.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTSPrimary, never()).fire(1);
    verify(mockTSSecondary, times(1)).fire(1);
  }

@Test
  public void fireTorpedo_Single_BothEmpty(){
    // Arrange
    when(mockTSPrimary.fire(1)).thenReturn(false);
    when(mockTSSecondary.fire(1)).thenReturn(false);
    when(mockTSPrimary.isEmpty()).thenReturn(true);
    when(mockTSSecondary.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockTSPrimary, never()).fire(1);
    verify(mockTSSecondary, never()).fire(1);
  }

  @Test
  public void fireTorpedo_All_PrimaryEmpty(){
    // Arrange
    when(mockTSPrimary.fire(1)).thenReturn(false);
    when(mockTSSecondary.fire(1)).thenReturn(true);
    when(mockTSPrimary.isEmpty()).thenReturn(true);
    when(mockTSSecondary.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockTSPrimary, never()).fire(1);
    verify(mockTSSecondary, times(1)).fire(1);
  }

@Test
  public void fireTorpedo_All_SecondaryEmpty(){
    // Arrange
    when(mockTSPrimary.fire(1)).thenReturn(true);
    when(mockTSSecondary.fire(1)).thenReturn(false);
    when(mockTSPrimary.isEmpty()).thenReturn(false);
    when(mockTSSecondary.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockTSPrimary, times(1)).fire(1);
    verify(mockTSSecondary, never()).fire(1);
  }

@Test
  public void fireTorpedo_All_BothEmpty(){
    // Arrange
    when(mockTSPrimary.fire(1)).thenReturn(false);
    when(mockTSSecondary.fire(1)).thenReturn(false);
    when(mockTSPrimary.isEmpty()).thenReturn(true);
    when(mockTSSecondary.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockTSPrimary, never()).fire(1);
    verify(mockTSSecondary, never()).fire(1);
  }

  @Test
  public void fireTorpedo_Single__PrimaryFiredLast_PrimaryEmpty(){
    // Arrange
    ship.wasPrimaryFiredLast = true;
    when(mockTSPrimary.fire(1)).thenReturn(false);
    when(mockTSSecondary.fire(1)).thenReturn(true);
    when(mockTSPrimary.isEmpty()).thenReturn(true);
    when(mockTSSecondary.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTSPrimary, never()).fire(1);
    verify(mockTSSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_PrimaryFiredLast_SecondaryEmpty(){
    // Arrange
    ship.wasPrimaryFiredLast = true;
    when(mockTSPrimary.fire(1)).thenReturn(true);
    when(mockTSPrimary.isEmpty()).thenReturn(false);
    when(mockTSSecondary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTSPrimary, times(1)).fire(1);
  }
@Test
  public void fireTorpedo_PrimaryFiredLast_Single_BothEmpty(){
    // Arrange
    ship.wasPrimaryFiredLast = true;
    when(mockTSPrimary.fire(1)).thenReturn(false);
    when(mockTSSecondary.fire(1)).thenReturn(false);
    when(mockTSPrimary.isEmpty()).thenReturn(true);
    when(mockTSSecondary.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockTSPrimary, never()).fire(1);
    verify(mockTSSecondary, never()).fire(1);
  }


}
