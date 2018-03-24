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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		sentenceStringList.add("Management is doing things right; leadership is doing the right things.");
		sentenceStringList.add("Whenever you see a successful business, someone once made a courageous decision.");
		sentenceStringList.add(
				"More business decisions occur over lunch and dinner than at any other time, yet no MBA courses are given on the subject.");
		sentenceStringList.add(
				"Management by objective works - if you know the objectives. Ninety percent of the time you don't.");
		sentenceStringList.add("Making good decisions is a crucial skill at every level.");
		sentenceStringList.add("What gets measured gets managed.");
		sentenceStringList.add("We know nothing about motivation. All we can do is write books about it.");
		sentenceStringList.add(
				"The leaders who work most effectively, it seems to me, never say \"I.\" And that's not because they have trained themselves not to say \"I.\" They don't think \"I.\" They think \"we\"; they think \"team.\" They understand their job to be to make the team function. They accept responsibility and don't sidestep it, but \"we\" gets the credit. This is what creates trust, what enables you to get the task done.");
		sentenceStringList.add("If you want something new, you have to stop doing something old");
		sentenceStringList
				.add("There is nothing so useless as doing efficiently that which should not be done at all.");
		sentenceStringList.add("No executive has ever suffered because his subordinates were strong and effective.");
		sentenceStringList.add("The productivity of work is not the responsibility of the worker but of the manager.");
		sentenceStringList.add("Time is the scarcest resource and unless it is managed nothing else can be managed.");
		sentenceStringList.add(
				"Meetings are by definition a concession to a deficient organization. For one either meets or one works. One cannot do both at the same time.");
		sentenceStringList
				.add("Marketing is not a function, it is the whole business seen from the customer's point of view.");
		sentenceStringList.add(
				"Trying to predict the future is like trying to drive down a country road at night with no lights while looking out the back window.");
		sentenceStringList.add("Doing the right thing is more important than doing the thing right.");
		sentenceStringList.add(
				"Most of what we call management consists of making it difficult for people to get their work done.");
		sentenceStringList.add(
				"The success and ultimately the survival of every business, large or small, depends in the last analysis on its ability to develop people. This ability is not measured by any of our conventional yardsticks of economic success; yet, is the final measurement.");
		sentenceStringList.add(
				"The fewer data needed, the better the information. And an overload of information, that is, anything much beyond what is truly needed, leads to information blackout. It does not enrich, but impoverishes.");
		sentenceStringList.add("The purpose of a business is to create a customer.");
		sentenceStringList.add("Business has only two basic functions - marketing and innovation.");
		sentenceStringList.add("Profitability is the sovereign criterion of the enterprise.");
		sentenceStringList.add(
				"The new information technology... Internet and e-mail... have practically eliminated the physical costs of communications.");
		sentenceStringList.add(
				"People who don't take risks generally make about two big mistakes a year. People who do take risks generally make about two big mistakes a year.");
		sentenceStringList.add("If you want something new, you have to stop doing something old");
		sentenceStringList.add(
				"Innovation is the specific instrument of entrepreneurship. The act that endows resources with a new capacity to create wealth.");
		sentenceStringList.add("The best way to predict the future is to create it.");
		sentenceStringList.add(
				"Since we live in an age of innovation, a practical education must prepare a man for work that does not yet exist and cannot yet be clearly defined.");
		sentenceStringList.add("Marketing and innovation make money. Everything else is a cost.");
		sentenceStringList
				.add("Innovation opportunities do not come with the tempest but with the rustling of the breeze.");
		sentenceStringList.add(
				"There are only two things in a business that make money - innovation and marketing, everything else is cost.");
		sentenceStringList.add(
				"Because its purpose is to create a customer, the business has two and only two functions: Marketing and Innovation. Marketing and Innovation produce results. All the rest are costs.");
		sentenceStringList.add(
				"Knowledge is the source of Wealth. Applied to tasks we already know, it becomes Productivity. Applied to tasks that are new, it becomes Innovation...");
		sentenceStringList.add(
				"Innovation requires us to systematically identify changes that have already occurred in a business - in demographics, in values, in technology or science - and then to look at them as opportunities. It also requires something that is most difficult for existing companies to do: to abandon rather than defend yesterday.");
		sentenceStringList.add("Business has only two basic functions - marketing and innovation.");
		sentenceStringList.add("Marketing and innovation produce results; all the rest are costs.");
		sentenceStringList.add(
				"Innovation is the specific tool of entrepreneurs, the means by which they exploit change as an opportunity for a different business or a different service. It is capable of being presented as a discipline, capable of being learned, capable of being practiced. Entrepreneurs need to search purposefully for the sources of innovation, the changes and their symptoms that indicate opportunities for successful innovation. And they need to know and to apply the principles of successful innovation.");
		sentenceStringList.add(
				"What we need is an entrepreneurial society in which innovation and entrepreneurship are normal, steady and continuous.");
		sentenceStringList.add(
				"Innovation is the specific tool of entrepreneurs, the means by which they exploit change as an opportunity for a different business or a different service.");

		sentenceStringList.add(
				"Thus, for those who are willing to go out into the field, to look and to listen, changing demographics is both a highly productive and a highly dependable innovation opportunity.");

		sentenceStringList.add(
				"The enterprise that does not innovate ages and declines. And in a period of rapid change such as the present, the decline will be fast.");

		sentenceStringList.add(
				"Whenever anything is being accomplished, it is being done, I have learned, by a monomaniac with a mission.");
		sentenceStringList.add(
				"No other area offers richer opportunities for successful innovation than the unexpected success.");
		sentenceStringList.add(
				"An established company which, in an age demanding innovation, is not able to innovation, is doomed to decline and extinction.\n"
						+ "");
		sentenceStringList.add(
				"Managing innovation will increasingly become a challenge to management, and especially to top management, and a touchstone of its competence.");
		sentenceStringList
				.add("Above all, innovation is not invention. It is a term of economics rather than of technology.");
		sentenceStringList
				.add("Effective innovations start small. They are not grandiose. They try to do one specific thing.");
		sentenceStringList.add("Making changes to better appeal to customer is INNOVATION.\n" + "");
		sentenceStringList.add("The most important thing in communication is hearing what isn't said.\n" + "");
		sentenceStringList.add("Management is doing things right; leadership is doing the right things.\n" + "");
		sentenceStringList.add(
				"Effective leadership is not about making speeches or being liked; leadership is defined by results not attributes.");
		sentenceStringList.add(
				"Management by objective works - if you know the objectives. Ninety percent of the time you don't.");
		sentenceStringList.add("Making good decisions is a crucial skill at every level.\n" + "");
		sentenceStringList.add(
				"The leaders who work most effectively, it seems to me, never say \"I.\" And that's not because they have trained themselves not to say \"I.\" They don't think \"I.\" They think \"we\"; they think \"team.\" They understand their job to be to make the team function. They accept responsibility and don't sidestep it, but \"we\" gets the credit. This is what creates trust, what enables you to get the task done.");
		sentenceStringList.add(
				"People who don't take risks generally make about two big mistakes a year. People who do take risks generally make about two big mistakes a year.\n"
						+ "");
		sentenceStringList.add(
				"Leadership is lifting a person's vision to high sights, the raising of a person's performance to a higher standard, the building of a personality beyond its normal limitations.");
		sentenceStringList.add("No executive has ever suffered because his subordinates were strong and effective.");
		sentenceStringList.add("Rank does not confer privilege or give power. It imposes responsibility.\n" + "");
		sentenceStringList.add(
				"Your first and foremost job as a leader is to take charge of your own energy and then help to orchestrate the energy of those around you.");
		sentenceStringList
				.add("Every organization must be prepared to abandon everything it does to survive in the future.");
		sentenceStringList.add("The best way to predict the future is to create it.");
		sentenceStringList.add(
				"Most of what we call management consists of making it difficult for people to get their work done.");
		sentenceStringList.add("The leader sees leadership as responsibility rather than as rank and privilege.");
		sentenceStringList.add("People learn the most when teaching others.");
		sentenceStringList.add(
				"Effective people are not problem minded; they're opportunity-minded. They feed opportunities and starve problems. They think preventively.\n"
						+ "");
		sentenceStringList.add("Leadership is defined by results not attributes.\n" + "");
		sentenceStringList.add(
				"People in any organization are always attached to the obsolete - the things that should have worked but did not, the things that once were productive and no longer are.");
		sentenceStringList.add("The building of a personality beyond its normal limitations.\n" + "");
		sentenceStringList.add(
				"Management means, in the last analysis, the substitution of thought for brawn and muscle, of knowledge for folkways and superstition, and of cooperation for force. It means the substitution of responsibility for obedience to rank, and of authority of performance for the authority of rank.");
		sentenceStringList.add("Leaders grow; they are not made.\n" + "");
		sentenceStringList.add(
				"The effective executive knows that it is easier to raise the performance of one leader than it is to raise the performance of a whole mass. She therefore makes sure she puts into the leadership position, into the standard-setting, the performance-making position the person who has the strength to do the outstanding pacesetting job. This always requires focus on the one strength of a person and dismissal of weaknesses as irrelevant unless they hamper the full deployment of the available strength.");
		sentenceStringList.add(
				"No institution can possibly survive if it needs geniuses or supermen to manage it. It must be organized in such a way as to be able to get along under a leadership composed of average human beings.\n"
						+ "");
		sentenceStringList.add("Accept the fact that we have to treat almost anybody as a volunteer.\n" + "");
		sentenceStringList.add(
				"Successful careers are not planned. They develop when people are prepared for opportunities because they know their strengths, their method of work, and their values. Knowing where one belongs can transform an ordinary person - hardworking and competent but otherwise mediocre - into an outstanding performer.");
		sentenceStringList.add("The best way to predict the future is to create it.\n" + "");
		sentenceStringList.add(
				"We live in an age of unprecedented opportunity: If you’ve got ambition and smarts, you can rise to the top of your chosen profession, regardless of where you started out.");
		sentenceStringList.add("Today knowledge has power. It controls access to opportunity and advancement.\n" + "");
		sentenceStringList
				.add("Innovation opportunities do not come with the tempest but with the rustling of the breeze.");
		sentenceStringList.add("Every single social and global issue of our day is a business opportunity in disguise");
		sentenceStringList.add(
				"Innovation requires us to systematically identify changes that have already occurred in a business - in demographics, in values, in technology or science - and then to look at them as opportunities. It also requires something that is most difficult for existing companies to do: to abandon rather than defend yesterday.");
		sentenceStringList.add(
				"Innovation is the specific tool of entrepreneurs, the means by which they exploit change as an opportunity for a different business or a different service. It is capable of being presented as a discipline, capable of being learned, capable of being practiced. Entrepreneurs need to search purposefully for the sources of innovation, the changes and their symptoms that indicate opportunities for successful innovation. And they need to know and to apply the principles of successful innovation.\n"
						+ "");
		sentenceStringList.add(
				"Effective people are not problem minded; they're opportunity-minded. They feed opportunities and starve problems. They think preventively.");
		sentenceStringList.add(
				"If general perception changes from seeing the glass as 'half-full' to seeing it as 'half empty' there are major innovative opportunities.");
		sentenceStringList.add("Results are obtained by exploiting opportunities, not by solving problems.");
		sentenceStringList.add(
				"Innovation is the specific tool of entrepreneurs, the means by which they exploit change as an opportunity for a different business or a different service.");
		sentenceStringList.add(
				"Thus, for those who are willing to go out into the field, to look and to listen, changing demographics is both a highly productive and a highly dependable innovation opportunity.");

		sentenceStringList.add(
				"No other area offers richer opportunities for successful innovation than the unexpected success.\n"
						+ "");

		sentenceStringList.add(
				"The real development I've seen of people in organizations, especially in big ones, comes from their being volunteers in a nonprofit organization - where you have responsibility, you see results, and you quickly learn what your values are. There is no better way to understand your strengths and discover where you belong than to volunteer in a nonprofit. That is probably the great opportunity for the social sector - and especially in its relationship to business.");
		sentenceStringList.add(
				"Progress is obtained only by exploiting opportunities, not by solving problems. When you solve problems, all you do is guarantee a return to normalcy.");
		sentenceStringList.add(
				"It is more productive to convert an opportunity into results than to solve a problem - which only restores the equilibrium of yesterday.\n"
						+ "");
		sentenceStringList.add("Most organizations staff their problems & starve their opportunities.\n" + "");
		sentenceStringList.add(
				"Tomorrow everybody - or practically everybody - will have had the education of the upper class of yesterday, and will expect equivalent opportunities. That is why we face the problem of making every kind of job meaningful and capable of satisfying every educated man.\n"
						+ "");
		sentenceStringList.add(
				"The most important work of the executive is to identify the changes that have already happened. The important thing . . . is to exploit the changes that have already occurred and to use them as opportunities.\n"
						+ "");
		sentenceStringList.add("Systematic change requires a willingness to look on change as an opportunity.");
		sentenceStringList.add(
				"What is the manager's job? It is to direct the resources and the efforts of the business toward opportunities for economically significant results. This sounds trite - and it is. But every analysis of actual allocation of resources and efforts in business that I have ever seen or made showed clearly that the bulk of time, work, attention, and money first goes to problems rather than to opportunities, and, secondly, to areas where even extraordinarily successful performance will have minimal impact on results.");

		sentenceStringList.add(
				"Many studies of research scientists have shown that achievement (at least below the genius level of an Einstein, Bohr, or a Planck) depends less on ability in doing research than on the courage to go after opportunity.\n"
						+ "");
		sentenceStringList.add("");
		sentenceStringList.add("");
		sentenceStringList.add("");
		sentenceStringList.add("");
		sentenceStringList.add("");
		sentenceStringList.add("");
		sentenceStringList.add("");
		sentenceStringList.add("");

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
