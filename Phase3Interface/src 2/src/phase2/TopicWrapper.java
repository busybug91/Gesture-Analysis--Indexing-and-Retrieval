package phase2;

public class TopicWrapper {

	private String _topic;
	private String _word;
	private double _probability;
	
	public String getTopic() { return _topic;}
	public String getWord() { return _word;}
	public double getProbablity() { return _probability;}

	public void setTopic(String value) { _topic = value;}
	public void setWord(String value) { _word = value;}
	public void setProbablity(double value) { _probability = value;}
	
	public TopicWrapper(String topic, String word, double prob)
	{
		_topic = topic;
		_word = word;
		_probability = prob;
	}
}