// The "Keys" class.
import java.io.*;
public class Keys
{
    private int data[];
    private String file;
    
    public Keys(String f)
    {
	data = new int[Consts.KEYSPERCLASS];
	try
	{
	    file = f;
	    FileReader fr = new FileReader (file);
	    BufferedReader br = new BufferedReader (fr);
	    int i = 0;
	    String s = br.readLine();
	    while( (s != null)&&(i < 5) ) //If there is still data in file, or if enough data was leeched out
	    {
		data[i] = Integer.parseInt(s);
		i++;
		s = br.readLine();
	    }
	    br.close();
	}
	catch(Exception e){System.out.println("Exception in Keys::Keys(): " + e.toString());};
    }
    
    public void setKey(int keyIndex, int changeTo)
    {
	if( (keyIndex >= 0) && (keyIndex < Consts.KEYSPERCLASS) )
	{
	    data[keyIndex] = changeTo;
	}
	this.save();
    }
    public int getKey(int keyIndex)
    {
	return data[keyIndex];
    }
    
    public void save()
    {
	try
	{
	    FileWriter fw = new FileWriter (file);
	    PrintWriter pw = new PrintWriter (fw);
	    for(int i = 0; i < Consts.KEYSPERCLASS; i++)
	    {
		pw.println(data[i]);
	    }
	    pw.close();
	}
	catch(Exception e){System.out.println("Exception in Keys::Save(): " + e.toString());};
    }
    public int check(int keyCode)
    {
	for(int i = 0; i < Consts.KEYSPERCLASS; i++)
	{
	    if(data[i] == keyCode)
	    {
		return i;
	    }
	}
	return -1;
    }
    
} // Keys class
