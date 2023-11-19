package jiraiyah.allthatmatters.utils;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.utils.data.ChunkData;

import java.io.*;

public final class ChunksSerializeManager
{
    public static final String NAME = "chunks.data";
    public static final String PATH = "literally_chunk_loader";

    public static boolean serialize(ChunkData areasData, String worldName)
    {
        FileOutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try
        {
            if (nonExistentFile(worldName))
            {
                File file = new File(getCompletePath(worldName));
                if (file.getParentFile().mkdirs() && file.createNewFile())
                {
                    AllThatMatters.LOGGER.info("Persistent chunks data file still doesn't exist, generating a new one");
                }
                else
                {
                    AllThatMatters.LOGGER.error("Unable to create chunks data file");
                }
            }
            outputStream = new FileOutputStream(getCompletePath(worldName));
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(areasData);
            outputStream.close();
            objectOutputStream.close();
            return true;
        }
        catch (IOException e)
        {
            AllThatMatters.LOGGER.error("Unable to serialize persistent chunks data, exception stack trace:");
            try
            {
                assert outputStream != null;
                outputStream.close();
                assert objectOutputStream != null;
                objectOutputStream.close();
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }

    public static ChunkData deserialize(String worldName)
    {
        FileInputStream inputStream = null;
        ObjectInputStream objectInputStream = null;
        try
        {
            if (nonExistentFile(worldName))
                return null;

            inputStream = new FileInputStream(getCompletePath(worldName));
            objectInputStream = new ObjectInputStream(inputStream);
            Object areasData = objectInputStream.readObject();
            inputStream.close();
            objectInputStream.close();
            if (areasData instanceof ChunkData)
                return (ChunkData) areasData;
            AllThatMatters.LOGGER.error("Unable to cast deserialized data to type class");
            return null;
        }
        catch (Exception e)
        {
            AllThatMatters.LOGGER.error("Unable to deserialize persistent chunks data, exception stack trace:");
            try
            {
                assert inputStream != null;
                inputStream.close();
                assert objectInputStream != null;
                objectInputStream.close();
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            e.printStackTrace();
            return null;
        }
    }

    private static boolean nonExistentFile(String worldName)
    {
        File file = new File(getCompletePath(worldName));
        return !file.exists();
    }

    public static String getCompletePath(String worldName)
    {
        return "mods" + File.separator + PATH + File.separator + worldName + File.separator + NAME;
    }
}