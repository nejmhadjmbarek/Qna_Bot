package com.example.bot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Sentence;
import com.example.entity.SentenceEntityRelation;
import com.example.entity.SentenceEntityRelationPK;
import com.example.repository.EntityRepository;
import com.example.repository.SentenceEntityRelationRepository;
import com.example.repository.SentenceRepository;
import com.google.cloud.language.v1.Entity;

@RestController
public class LoadDataController {
	@Autowired
	SentenceRepository sentenceRepository;
	@Autowired
	EntityRepository entityRepository;

	@Autowired
	SentenceEntityRelationRepository sentenceEntityRelationRepository;

	@RequestMapping(value = "/store/sentence")
	public String storeSentenceToDataBase() {

		List<String> sentenceStringList = new ArrayList<>();
		sentenceStringList.add("The most important thing in communication is hearing what isn't said.");
		sentenceStringList.add(
				"Listening (the first competence of leadership) is not a skill, it is a discipline. All you have to do is keep your mouth shut.\n"
						+ "");
		sentenceStringList.add(
				"Thus, for those who are willing to go out into the field, to look and to listen, changing demographics is both a highly productive and a highly dependable innovation opportunity.");
		sentenceStringList.add("To improve communications, work not on the utter, but the recipient.");

		sentenceStringList.add("Listening is not a skill; it is a discipline.");
		sentenceStringList.add(
				"Never ask who's right. Start out by asking what is right. And you find that out by listening to dissenting, disagreeing opinions. ");

		sentenceStringList
				.add("The entrepreneur always searches for change, responds to it, and exploits it as an opportunity.");

		sentenceStringList.add("Whenever you see a successful business, someone once made a courageous decision.");

		sentenceStringList.add(
				"More business decisions occur over lunch and dinner than at any other time, yet no MBA courses are given on the subject.");
		sentenceStringList.add("Making good decisions is a crucial skill at every level.");
		sentenceStringList.add(
				"The 1st question the effective decision-maker asks is: 'Is this a generic situation or an exception?");
		sentenceStringList.add(
				"Checking the results of a decision against its expectations shows executives what their strengths are, where they need to improve, and where they lack knowledge or information.");
		sentenceStringList.add("Once the facts are clear the decisions jump out at you.");
		sentenceStringList.add(
				"No decision has been made unless carrying it out in specific steps has become someone's work assignment and responsibility.");
		sentenceStringList.add(
				"Strategic planning is the continuous process of making present entrepreneurial (risk-taking) decisions systematically and with the greatest knowledge of their futurity; organizing systematically the efforts needed to carry out these decisions; and measuring the results of these decisions against the expectations through organized, systematic feedback.");
		sentenceStringList.add(
				"The most common source of mistakes in management decisions is the emphasis on finding the right answer rather than the right question.");
		sentenceStringList.add("Erroneous assumptions can be disastrous.");
		sentenceStringList.add(
				"Long range planning does not deal with future decisions, but with the future of present decisions.");
		sentenceStringList.add(
				"Unless a decision has degenerated into work, it is not a decision; it is at best a good intention.");
		sentenceStringList.add(
				"In areas where they are simply incompetent, smart executives don’t make decisions or take actions. They delegate.");
		sentenceStringList.add(
				"The largest 100 corporations hold 25 percent of the worldwide productive assets, which in turn control 75 percent of international trade and 98 percent of all foreign direct investment.The multinational corporation...puts the economic decision beyond the effective reach of the political process and its decision-makers, national governments.");
		sentenceStringList.add(
				"Most discussions of decision making assume that only senior executives make decisions or that only senior executives' decisions matter. This is a dangerous mistake.");
		sentenceStringList.add(
				"Teamwork is neither \"good\" nor \"desirable.\" It is a fact. Wherever people work together or play together they do so as a team. Which team to use for what purpose is a crucial, difficult and risky decision that is even harder to unmake. Managements have yet to learn how to make it.");
		sentenceStringList
				.add("One has to make a decision when a condition is likely to degenerate if nothing is done.\n");
		sentenceStringList.add(
				"Executives owe it to the organization and to their fellow workers not to tolerate nonperforming individuals in important jobs.");
		sentenceStringList.add(
				"Some of the best business and nonprofit CEOs I've worked with over a sixty-five-year consulting career were not stereotypical leaders. They were all over the map in terms of their personalities, attitudes, values, strengths, and weaknesses.");
		sentenceStringList.add(
				"Political freedom is neither easy nor automatic, neither pleasant nor secure. It is the responsibility of the individual for the decisions of society as if they were his own decisions-as in moral truth and accountability they are.");
		sentenceStringList.add(
				"The most important decisions in organizations are people decisions, and yet only the military, and only recently, has begun to ask, \"If we assign this general to lead this base, what do we expect him to accomplish?\"");
		sentenceStringList.add(
				"The question that faces the strategic decision maker is not what his organisation should do tomorrow. It is, what do we have to do today to be ready for an uncertain tomorrow?");
		sentenceStringList.add("Decisions exist only in the present.");
		sentenceStringList.add(
				"Important decisions are risky. They should be controversial. Acclamation means that nobody has done the homework.");
		sentenceStringList
				.add("The entrepreneur always searches for change, responds to it, and exploits it as an opportunity.");
		sentenceStringList.add(
				"Follow effective action with quiet reflection. From the quiet reflection will come even more effective action.");
		sentenceStringList.add("The most important thing in communication is hearing what isn't said.");
		sentenceStringList.add("Management is doing things right; leadership is doing the right things.");
		sentenceStringList.add("Whenever you see a successful business, someone once made a courageous decision.");
		sentenceStringList.add(
				"Effective leadership is not about making speeches or being liked; leadership is defined by results not attributes.");
		sentenceStringList.add(
				"Management by objective works - if you know the objectives. Ninety percent of the time you don't.");
		sentenceStringList.add("Entrepreneurship is neither a science nor an art. It is a practice.");
		sentenceStringList.add(
				"People who don't take risks generally make about two big mistakes a year. People who do take risks generally make about two big mistakes a year.");
		sentenceStringList.add("Do first things first, and second things not at all.");
		sentenceStringList
				.add("There is nothing so useless as doing efficiently that which should not be done at all.");
		sentenceStringList.add("No executive has ever suffered because his subordinates were strong and effective.");
		sentenceStringList.add("Unless commitment is made, there are only promises and hopes... but no plans.");
		sentenceStringList.add("Knowledge has to be improved, challenged, and increased constantly, or it vanishes.");
		sentenceStringList.add(
				"Innovation is the specific instrument of entrepreneurship. The act that endows resources with a new capacity to create wealth.");
		sentenceStringList.add("No one learns as much about a subject as one who is forced to teach it.");
		sentenceStringList.add("The best way to predict the future is to create it.");
		sentenceStringList.add("It's more important to do the right thing than to do things right.");
		sentenceStringList.add(
				"Quality in a service or product is not what you put into it. It is what the client or customer gets out of it.");
		sentenceStringList.add(
				"There is the risk you cannot afford to take, and there is the risk you cannot afford not to take.");
		sentenceStringList.add("The most effective way to manage change is to create it.");
		sentenceStringList.add(
				"The single most important thing to remember about any enterprise is that there are no results inside its walls. The result of a business is a satisfied customer.");
		sentenceStringList.add(
				"Long range planning does not deal with future decisions, but with the future of present decisions.");
		sentenceStringList.add(
				"The only things that evolve by themselves in an organization are disorder, friction and malperformance.");
		sentenceStringList.add(
				"The talk you hear about adapting to change is not only stupid, it's dangerous. The only way you can manage change is to create it.");
		sentenceStringList.add("Management is doing things right; leadership is doing the right things.");
		sentenceStringList.add(
				"Management by objective works - if you know the objectives. Ninety percent of the time you don't.");
		sentenceStringList.add(
				"People who don't take risks generally make about two big mistakes a year. People who do take risks generally make about two big mistakes a year.");
		sentenceStringList
				.add("There is nothing so useless as doing efficiently that which should not be done at all.");
		sentenceStringList.add("The productivity of work is not the responsibility of the worker but of the manager.");
		sentenceStringList.add("Time is the scarcest resource and unless it is managed nothing else can be managed.");
		sentenceStringList.add("The best way to predict the future is to create it.");
		sentenceStringList.add(
				"Most of what we call management consists of making it difficult for people to get their work done.");
		sentenceStringList.add(
				"I have been saying for many years that we are using the word 'guru' only because 'charlatan' is too long to fit into a headline.");
		sentenceStringList.add(
				"Innovation requires us to systematically identify changes that have already occurred in a business - in demographics, in values, in technology or science - and then to look at them as opportunities. It also requires something that is most difficult for existing companies to do: to abandon rather than defend yesterday.");
		sentenceStringList.add(
				"One cannot buy, rent or hire more time. The supply of time is totally inelastic. No matter how high the demand, the supply will not go up. There is no price for it. Time is totally perishable and cannot be stored. Yesterday's time is gone forever, and will never come back. Time is always in short supply. There is no substitute for time. Everything requires time. All work takes place in, and uses up time. Yet most people take for granted this unique, irreplaceable and necessary resource.");
		sentenceStringList.add(
				"Checking the results of a decision against its expectations shows executives what their strengths are, where they need to improve, and where they lack knowledge or information.");
		sentenceStringList.add(
				"what's absolutely unforgivable is the financial benefit top management people get for laying off people. There is no excuse for it. No justification. This is morally and socially unforgivable, and we will pay a heavy price for it.");
		sentenceStringList.add("You can't manage knowledge.Knowledge is between two ears and only between two ears.");
		sentenceStringList.add(
				"The most common source of mistakes in management decisions is the emphasis on finding the right answer rather than the right question.");
		sentenceStringList.add("A manager is responsible for the application and performance of knowledge.");
		sentenceStringList.add(
				"Strategic management is not a box of tricks or a bundle of techniques. It is analytical thinking and commitment of resources to action. But quantification alone is not planning. Some of the most important issues in strategic management cannot be quantified at all.");
		sentenceStringList.add(
				"The great challenge to management today is to make productive the tremendous new resource, the knowledge worker. This, rather than the productivity of the manual worker, is the key to economic growth and economic performance in today's society.");
		sentenceStringList.add(
				"Management means, in the last analysis, the substitution of thought for brawn and muscle, of knowledge for folkways and superstition, and of cooperation for force. It means the substitution of responsibility for obedience to rank, and of authority of performance for the authority of rank.");
		sentenceStringList.add(
				"There's no such thing as knowledge management; there are only knowledgeable people. Information only becomes knowledge in the hands of someone who knows what to do with it.");
		sentenceStringList.add("If you want to improve how you manage time - stop doing what doesn't need to be done!");
		sentenceStringList.add("Management is not being brilliant. Management is being conscientious.");
		sentenceStringList.add(
				"The most important, and indeed the truly unique, contribution of management in the 20th century was the fifty-fold increase in the productivity of the MANUAL WORKER in manufacturing. The most important contribution management needs to make in the 21st century is similarly to increase the productivity of KNOWLEDGE WORK and the KNOWLEDGE WORKER.");
		sentenceStringList.add(
				"In the knowledge economy everyone is a volunteer, but we have trained our managers to manage conscripts.");
		sentenceStringList.add(
				"(Waste = Loss): The first rule of business is to survive and the guiding principle of business economics is not the maximisation of profit, it is the avoidance of loss");

		/****************************************************************************************/

		for (String text : sentenceStringList) {
			Sentence sentence = new Sentence();

			sentence.setSentence(text);
			sentence.setLanguage("en");
			sentenceRepository.save(sentence);

			CloodApiFunction CloudApi = new CloodApiFunction();
			List<Entity> sentenceEntities = new ArrayList<>();
			try {
				sentenceEntities = CloudApi.analyzeEntitiesText(text);
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (Entity entity : sentenceEntities) {

				com.example.entity.Entity entityDb = new com.example.entity.Entity();

				entityDb.setNameEntity(entity.getName());
				entityDb.setSalience(entity.getSalience());

				entityDb = entityRepository.save(entityDb);

				SentenceEntityRelationPK sentenceEntityRelationPK = new SentenceEntityRelationPK();

				sentenceEntityRelationPK.setIdEntity(entityDb.getIdEntity());
				sentenceEntityRelationPK.setIdSentence(sentence.getIdSentence());

				SentenceEntityRelation sentenceEntityRelation = new SentenceEntityRelation();
				sentenceEntityRelation.setSentenceRelationPK(sentenceEntityRelationPK);
				sentenceEntityRelation.setEntity(entityDb);
				sentenceEntityRelation.setSentence(sentence);
				sentenceEntityRelationRepository.save(sentenceEntityRelation);
			}

		}

		return "succeeed";

	}

}
