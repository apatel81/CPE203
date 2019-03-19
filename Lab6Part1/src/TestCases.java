import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

import org.junit.Test;
import org.junit.Before;
import sun.jvm.hotspot.debugger.win32.coff.COMDATSelectionTypes;

import static org.junit.Assert.*;

public class TestCases
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator1()
   {
      ArtistComparator artistComparator = new ArtistComparator();
      assertTrue(artistComparator.compare(songs[0], songs[1]) < 0);
   }

   @Test
   public void testArtistComparator2()
   {
      ArtistComparator artistComparator = new ArtistComparator();
      assertFalse(artistComparator.compare(songs[5], songs[2]) < 0);
   }

   @Test
   public void testLambdaTitleComparator1()
   {
      Comparator <Song> songComparator =
              (s1, s2) -> s1.getTitle().compareTo(s2.getTitle());
      assertTrue(songComparator.compare(songs[0], songs[1]) > 0);
   }

   @Test
   public void testLambdaTitleComparator2()
   {
      Comparator <Song> songComparator =
              (s1, s2) -> s1.getTitle().compareTo(s2.getTitle());
      assertFalse(songComparator.compare(songs[7], songs[6]) > 0);
   }

   @Test
   public void testYearExtractorComparator1()
   {
      Comparator<Song> songComparator = Comparator.comparing(Song::getYear);
      assertTrue(songComparator.compare(songs[2], songs[3]) > 0);
   }

   @Test
   public void testYearExtractorComparator2()
   {
      Comparator<Song> songComparator = Comparator.comparing(Song::getYear);
      assertFalse(songComparator.compare(songs[6], songs[7]) > 0);
   }

   @Test
   public void testComposedComparator1()
   {
      Comparator<Song> c1 = Comparator.comparing(Song::getArtist);
      Comparator<Song>  c2 = Comparator.comparing(Song::getYear);
      ComposedComparator composedComparator = new ComposedComparator(c1, c2);
      assertTrue(composedComparator.compare(songs[3], songs[7]) < 0);
   }

   @Test
   public void testComposedComparator2()
   {
      Comparator<Song> c1 = Comparator.comparing(Song::getArtist);
      Comparator<Song>  c2 = Comparator.comparing(Song::getYear);
      ComposedComparator composedComparator = new ComposedComparator(c1, c2);
      assertFalse(composedComparator.compare(songs[5], songs[7]) > 0);
   }

   @Test
   public void testThenComparing1()
   {
      Comparator<Song> songComparator = Comparator.comparing(Song::getArtist).thenComparing(Song::getYear);
      assertTrue(songComparator.compare(songs[3], songs[5]) > 0);
   }

   @Test
   public void testThenComparing2()
   {
      Comparator<Song> songComparator = Comparator.comparing(Song::getArtist).thenComparing(Song::getYear);
      assertFalse(songComparator.compare(songs[5], songs[7]) > 0);
   }

   @Test
   public void runSort1()
   {
      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );


      Comparator<Song> songComparator = Comparator.comparing(Song::getArtist).thenComparing(Song::getTitle).thenComparing(Song::getYear);


      songList.sort(
         // pass comparator here
              songComparator
      );

      assertEquals(songList, expectedList);
   }
}
