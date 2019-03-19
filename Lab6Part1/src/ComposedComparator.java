import java.util.Comparator;

public class ComposedComparator implements Comparator<Song>
{
    private Comparator<Song> c1;
    private Comparator<Song> c2;

    public ComposedComparator(Comparator<Song> c1, Comparator<Song> c2)
    {
        this.c1 = c1;
        this.c2 = c2;
    }

    public int compare(Song s1, Song s2)
    {
        if (s1.getArtist().compareTo(s2.getArtist()) == 0)
        {
            return (s2.getYear() - s1.getYear());
        }

        return (s1.getArtist().compareTo(s2.getArtist()));
    }


}
