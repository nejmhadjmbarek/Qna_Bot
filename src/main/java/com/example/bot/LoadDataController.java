package com.example.bot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Sentence;
import com.example.entity.SentenceEntityRelation;
import com.example.entity.SentenceEntityRelationPK;
import com.example.repository.EntityRepository;
import com.example.repository.SentenceEntityRelationRepository;
import com.example.repository.SentenceRepository;
import com.google.cloud.language.v1.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LoadDataController {
	@Autowired
	SentenceRepository sentenceRepository;
	@Autowired
	EntityRepository entityRepository;

	@Autowired
	SentenceEntityRelationRepository sentenceEntityRelationRepository;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(LoadDataController.class);

	@RequestMapping(value = "/store/sentence")
	public String storeSentenceToDataBase() {

		List<String> sentenceStringList = new ArrayList<>();

		sentenceStringList.add("目標は絶対のものではなく、方向を示すものである。");
		sentenceStringList.add(
				"企業の目的と使命を定義するとき、出発点はひとつしかない。顧客である。顧客を満足させることが企業の使命であり、目的である。	企業の目的と使命を定義するとき、出発点はひとつしかない。顧客である。顧客を満足させることが企業の使命であり、目的である。");
		sentenceStringList.add("成果をあげる者は、新しい活動を始める前に必ず古い活動を捨てる。");
		sentenceStringList.add("成果をあげる者は、新しい活動を始める前に必ず古い活動を捨てる。	成果をあげる者は、新しい活動を始める前に必ず古い活動を捨てる。");
		sentenceStringList.add("まず何よりも、変化を脅威ではなく機会としてとらえなければならない。");
		sentenceStringList.add("我々は今いる人間をもって組織をマネジメントしなければならない。");
		sentenceStringList.add("「私は」ではなく「我々は」を考えることが大切。");
		sentenceStringList.add("リスクが富を生む。");
		sentenceStringList.add("成功してきたのと同じ貢献を続けていたのでは、失敗する。");
		sentenceStringList.add("一人の力で成功することは絶対にない。一人の力が他人の協力を得たとき、初めて事業は成功する。");
		sentenceStringList.add("人間は「自分でなければできない」と錯覚していることが多すぎる。");
		sentenceStringList.add("真摯さはごまかせない。");
		sentenceStringList.add("チャンスは一念専心によって、かろうじて得ることが出来る。");
		sentenceStringList.add("すべての仕事について、まったくしなかったならば何が起こるかを考える。何も起こらないが答えであるならば、その仕事は直ちにやめるべきである。");
		sentenceStringList.add("計画は紙の上で消える。よき意図の表明に終わる。実行されることは稀である。");
		sentenceStringList.add("業績を上げる最大のカギは責任感である。権威や権限ではない。");
		sentenceStringList.add("過去のリーダーの仕事は「命じること」だが、未来のリーダーの仕事は「聞くこと」が重要になる。");
		sentenceStringList.add("時間の使い方は練習によって改善できる。だが、たえず努力しない限り仕事に流される。");
		sentenceStringList.add("知的労働においては、時間の活用と浪費の違いは、成果と業績に直接現れる。");
		sentenceStringList.add("クライメットクリエイターが経営者だ。");
		sentenceStringList.add("【覚え書き｜クライメットクリエイター＝組織や場の気候・空気・雰囲気をつくる人】");
		sentenceStringList.add("何事かを成し遂げるのは、強みによってである。弱みによって何かを行うことはできない。できないことによって何かを行うことなど、到底できない。");
		sentenceStringList.add("これからは、誰もが自らをマネジメントしなければならない。自らを最も貢献できる場所に置き、成長していかなければならない。");
		sentenceStringList.add(
				"私の観察によれば、成果をあげる者は仕事からスタートしない。時間からスタートする。計画からもスタートしない。何に時間がとられているかを明らかにすることからスタートする。次に、時間を管理すべく自らの時間を奪おうとする非生産的な要素を退ける。");
		sentenceStringList.add(
				"いかなる成果もあげられない人の方がよく働いている。成果の上がらない人は、第一に、ひとつの仕事に必要な時間を過小評価する。第二に、彼らは急ごうとする。第三に、彼らは同時にいくつかのことをしようとする。");
		sentenceStringList.add("イノベーションを成功させるには、焦点を絞り単純なものにしなければならない。");
		sentenceStringList.add("時間を管理するには、まず自らの時間をどのように使っているかを知らなければならない。");
		sentenceStringList.add("誰でも自らの強みについてはよく分かっている。だが、たいていは間違っている。わかっているのはせいぜい弱みである。それさえ間違っていることが多い。");
		sentenceStringList.add(
				"組織に働く者は、組織の使命が社会において重要であり、他のあらゆるものの基盤であるとの信念を持たねばならない。この信念がなければ、いかなる組織といえども、自信と誇りを失い、成果をあげる能力を失う。");
		sentenceStringList.add("原因は何十年かのちに学者が明らかにするだろうが、行動する経営者としては待っていられないだろう。使えるもの、分かったことはどんどん使いなさい。");
		sentenceStringList.add("急成長会社では、無能な者が要職にいる。会社の成長についていけなかった人々である。");
		sentenceStringList.add("経営者は、その企業の将来について、もっと時間と思索を割くべきである。");
		sentenceStringList.add("幹部の仕事と知識とは、あまり関係はない。");
		sentenceStringList.add("他人の短所が目につきすぎる人は、経営者には向いていない。長所を効果的に発揮させるのが自分の仕事だと考える人が、有能な経営者になれる。");
		sentenceStringList.add("人々を動機付ける能力がなくては、経営者とは言えない。");
		sentenceStringList.add("決断の場面においては、トップは常に孤独である。");
		sentenceStringList.add("組織に働く者は、成果に何も寄与しないが無視できない仕事に時間をとられる。膨大な時間が、ほとんど役に立たない仕事、あるいはまったく役に立たない仕事に費やされている。");
		sentenceStringList.add("経営者が学びえないが、どうしても身につけていかなければならない資質がひとつある。それは品性だ。");
		sentenceStringList.add("誰かが勇気ある決断をしなければ、どんな事業も成功しないだろう。");
		sentenceStringList.add("組織が存在するのは組織自身のためではない。自らの機能を果たすことによって、社会、コミュニティー、個人のニーズを満たすためである。組織とは目的ではなく手段である。");
		sentenceStringList.add("驕るな。企業は社会に存在させていただいているものだ。");
		sentenceStringList.add("改善の目的は、製品やサービスを改良し、２・３年後にはまったく新しい製品やサービスにしてしまうことである。");
		sentenceStringList.add(
				"仕事や成果を大幅に改善するための唯一の方法は、成果を上げるための能力を向上させることである。際立って優れた能力を持つ人を雇うことはできる。あるいは際立って優れた知識を持つ人を雇うこともできる。だが、いかに努力したとしても、能力と知識の向上に関しては、大幅な期待をすることはできない。もはや、これ以上は不可能か、あるいはすくなくとも効果のあまりないような限界に達している。新種のスーパーマンを育てることはできない。現在の人間をもって、組織をマネジメントしなければならない。");
		sentenceStringList.add(
				"我々は、一つの重要な分野で強みを持つ人が、その強みをもとに仕事を行えるよう、組織を作ることを学ばなければならない。仕事振りの向上は、人間の能力の飛躍的な増大ではなく、仕事の方法の改善によって図られなければならない。知識についても同じことが言える。優れた知識を大量に持つ人を大量に手に入れようとしても、そのために必要な費用が期待できる成果に比べて高すぎる。");
		sentenceStringList.add(
				"成果をあげる人の共通しているのは、自らの能力や存在を成果に結びつける上で、必要とされている習慣的な力である。企業や政府機関で働いていようと、病院の理事長や大学の学長であろうと、まったく同じである。私の知る限り、知能や勤勉さ、想像力や知識がいかに優れようと、そのような習慣的な力に欠ける人は成果をあげることができなかった。成果をあげることは一つの習慣である。習慣的な能力の蓄積である。習慣的な能力は、常に習得に努めることが必要である。習慣になるまで、いやになるほど反復しなければならない。");
		sentenceStringList.add(
				"私は、成果をあげる人間のタイプなどというものは存在しないことをかなり前に気づいた。私が知っている成果をあげる人たちは、その気性や能力、仕事や仕事の方法、性格や知識や関心において千差万別だった。共通点は、なすべきことを成し遂げる能力を持っていたことだけだった。");
		sentenceStringList.add(
				"どんな分野でも、普通の人であれば並みの能力は身につけられる。卓越することはできないかもしれない。卓越するには、特別の才能が必要だからである。だが、成果を上げるには、成果を上げるための並みの能力で十分である。");
		sentenceStringList.add(
				"組織の活動や業績に実質的な貢献をなすべき知識労働者は、すべてエグゼクティブである。組織の活動や業績とは、企業の場合新製品を出すことであり、市場で大きなシェアを獲得することである。病院の場合は、患者に優れた医療サービスを提供することである。組織のそのような能力に実質的な影響を及ぼすために、知識労働者は意思決定をしなければならない。命令に従って行動すればよいというわけにはいかない。自らの貢献について責任を負わなければならない。");
		sentenceStringList.add(
				"知識労働者を直接、あるいは細かく監督することはできない。彼らには助力を与えることができるだけである。知識労働者は自らをマネジメントしなければならない。自らの仕事を業績や貢献に結び付けるべく、すなわち成果を上げるべく、自らをマネジメントしなければならない。");
		sentenceStringList.add(
				"ものごとをなすべき者の仕事は、成果を上げることである。ものごとをなすということは、成果を上げるということである。企業、病院、政府機関、労働組合、軍隊のいずれにあろうとも、そこに働くものは常に、なすべきことをなすことを期待される。それにもかかわらず、ものごとをなすべき者のうち、大きな成果を上げている者は少ない。");
		sentenceStringList.add(
				"知識労働は三種類ある。第一に仕事の成果が純粋に質の問題であるもの。第二に、質と量を共に成果とすべきもの。第三に仕事の成果が肉体労働と同類の仕事が多数あるもの。知識労働の生産性を高めるには、その仕事が成果に関して、いずれの範疇に属するかを知っておく必要がある。");
		sentenceStringList.add(
				"知識労働の生産性の向上を図る場合にまず問うべきは、「何が目的か。何を実現しようとしているか。なぜそれを行うか」である。手っ取り早く、しかも、おそらく最も効果的に知識労働の生産性を向上させる方法は、仕事を定義しなおすことである。とくに、行う必要のない仕事をやめることである。");
		sentenceStringList.add(
				"我々が強い衝撃を持って最初に学んだことは、知識労働においては、資本は労働（すなわち人間）の代わりにはならないということである。経済学の用語に従えば、肉体労働については、資本と技術は生産要素である。しかし知識労働については、もはやそれらは生産手段であるにすぎない。資本と技術が仕事の生産性を高めるか損ねるかについては、知識労働者がそれらを使って何をいかにするかにかかっている。仕事の目的や、使う人の技量にかかっている。");
		sentenceStringList.add(
				"組織は変化に対応するために高度に分権化する必要がある。意思決定を迅速に行わなければならないからである。その意思決定は、成果と市場に密着し、技術に密着し、さらにイノベーションの機会として利用すべき社会、環境、人口構造、知識の変化に密着して行わなければならない。");
		sentenceStringList.add(
				"組織は新しいものの創造に専念しなければならない。具体的には、あらゆる組織が三つの体系的な活動に取り組む必要がある。第一に、行うことすべてについて耐えざる改善を行う必要がある。第二に知識の開発、すなわちすでに成功しているものについて、さらに新しい応用法を開発する必要がある。第三にイノベーションの方法を学ぶ必要がある。イノベーションは体系的なプロセスとして組織化することができるし、まさにそのように組織化しなければならない。");
		sentenceStringList.add(
				"新しい組織社会では、知識を有するあらゆる者が、４・５年おきに新しい知識を仕入れなければならない。さもなければ時代遅れとなる。このことは、知識に対して最大の影響を与える変化が、その知識の領域で起こるようになっていることからも、重大な意味を持つ。新しい知識を生み、古い知識を陳腐化させるものは、科学や技術とは限らない。社会的なイノベーションのほうが大きな役割を果たすことが多い。");
		sentenceStringList.add(
				"昔の人は言った。「夕食の客には教育ある人が良い。しかし砂漠では、教育のある人よりも何かのやり方を知っている人が必要だ。教育ある人間はいらない」。事実すでにアメリカの大学では、伝統的な教養人は、教育ある人間とさえ見なされなくなっている。そのような者は、趣味人として一段下に見られている。");
		sentenceStringList.add(
				"３つの段階、産業革命、生産性革命、マネジメント革命の根本にあったものが、知識における意味の変化だった。こうして我々は一般知識から専門知識へと移行してきた。かつての知識は一般知識だった。これに対し、今日知識とされているものは、必然的に高度の専門知識である。");
		sentenceStringList.add(
				"知識がなんなるいくつかの資源のうちの一つではなく、資源の中核になったという事実によって、我々の社会はポスト資本主義社会となる。この事実は社会の構造を根本から変える。新しい社会の力学を生み出し、新しい経済の力学を生む。そして新しい政治を生む。");
		sentenceStringList.add(
				"土地、労働、資本は制約条件でしかない。それらのものがなければ、知識といえども、何も生み出せない。だが今日では、効果的なマネジメント、すなわち知識の知識に対する適用が行われさえすれば、他の資源はいつでも手に入れられるようになっている。");
		sentenceStringList.add(
				"多くの人にとって、マネジメントと言えば企業経営を意味する。だがそれは、単にマネジメントが最初に現れたのが大企業だったからにすぎない。５０年ほど前、マネジメントの研究に取り組んだとき、私も企業のマネジメントに焦点を当てていた。しかしやがて、企業であれ企業以外であれ、あらゆる近代組織において、マネジメントの研究に取り組んだとき、マネジメントの必要性が明らかになっていった。");
		sentenceStringList.add(
				"知識労働者が貢献に焦点をあわせることは必須である。それなくして、彼らが貢献する術はない。知識労働者が生産するのは、物ではなくアイデアや情報やコンセプトである。知識労働者は、ほとんどが専門家である。事実彼らは、通常、ひとつのことだけを非常に良く行えるとき、すなわち専門化したときのみ大きな成果を上げる。それだけでは不毛である。専門家の産出物は、他の専門家の産出物と統合されて初めて成果となる。");
		sentenceStringList.add("昨日を捨てよ。自分が得意だと思っていることに溺れるな。物事の「本質」を鋭く透察する心を持て。");
		sentenceStringList.add("定年の必要は実際のところ、年老いたということではない。おもな理由は、若者たちに道をあけなければならないということにある。");
		sentenceStringList.add("手っ取り早く、効果的に生産性を向上させる方法は、何を行うべきかを明らかにすることである。そして、行う必要のない仕事をやめることである。");
		sentenceStringList.add("未来を語る前に、今の現実を知らなければならない。現実からしかスタートできないからである。");
		sentenceStringList.add(
				"未来に何かを起こすには、勇気を必要とする。努力を必要とする。信念を必要とする。決定のためには、いろいろな案がなくてはならない。ただし、可・否の二案だけでは不足だ。決定しないという決定もあることを忘れない方がいい。反対論がない場合には結論を出してはならない。勇気と勉強に不足があれば反対論は出ない。");
		sentenceStringList.add("本物の変化とは人が行うことであり、流行とは人が言うことである。");
		sentenceStringList.add(
				"数百年後、歴史家が長い視点から今日の時代をとらえた場合、最も重要な出来事はテクノロジーでもインターネットでも電子商取引でもないだろう。人間がおかれた状況の史上例を見ない変動こそ、最大の出来事である。今日多くの人々が選択する自由を手にしており、その人数は急激に増えつつある。これは歴 史上まったくなかったことだ。それは同じく史上初めて人々が自分自身をマネジメントしなければならないことでもある。しかし、社会の側ではこの事態に対応 する準備が全然できていない。");
		sentenceStringList.add("専門性の進化と、異分野との接触のバランスを実現しなければならない。");
		sentenceStringList.add("学校は長くいればいるほど、自分で意志決定を行う機会が少なくなる。");
		sentenceStringList.add("知識労働とサービス労働は、何を行うかどのような技能によって行うかによって生産性が左右される。");
		sentenceStringList.add("成果をあげる人たちは気性や能力、職種や仕事のやり方、性格や知識や関心において千差万別である。共通点は、なすべきことを成し遂げる能力をもっていることだけである。");
		sentenceStringList.add("未来を予測する最良の方法は、未来を創ることだ。未来を予測しようとすると罠にはまる。");
		sentenceStringList.add("たいていの経営者は、その時間の大半を過ぎ去った「きのう」の諸問題に費やしている。");
		sentenceStringList.add("自らが自らに求めるものが少なければ成長しない。だが多くを求めるならば、何も成長しない者と同じ程度の努力で、巨人にまで成長する。");
		sentenceStringList.add("なにかが成し遂げられるときには、かならずその使命のほかには何も考えられない偏執狂的な人間がいるものだ。");
		sentenceStringList.add(
				"学ぶことのできない資質、習得することができず、もともと持っていなければならない資質がある。他から得ることができず、どうしても自ら身につけていなければならない資質がある。才能ではなく真摯さである。");
		sentenceStringList.add("ビジネスの目的の正しい定義はただひとつ。顧客を作り出すことである。");
		sentenceStringList.add("できることから始めるのではなく、正しいことから始めるのです。");
		sentenceStringList.add("ビジネスには二つの機能しかない。マーケティングとイノベーションである。");
		sentenceStringList.add("計画とは未来に関する現在の決定である。");
		sentenceStringList.add("生産性とは機械や道具や手法の問題ではなく、姿勢の問題である。換言するならば、生産性を決定するものは、働く人たちの動機である。");
		sentenceStringList.add("成功した企業は、きまって誰かがかつて勇気ある決断をした。");
		sentenceStringList.add("将来についてわかっている唯一のことは、今とは違うということだ。");
		sentenceStringList.add("時間は最も乏しい資源であり、それが管理できなければ他の何事も管理することはできない。");
		sentenceStringList.add("マネジメントは物事を正しく行う事で、リーダーシップとは正しい事をすることである。");
		sentenceStringList.add("コンサルタントとしての私の最大の長所は無知になりいくつかの質問をすることである。");
		sentenceStringList.add("コミュニケーションで最も大切なことは、相手の言わない本音の部分を聞くことである。");
		sentenceStringList.add("経済的発展において最大の資源となるのは人間である。経済を発展させるのは、人間であって、資本や原料ではない。");
		sentenceStringList.add("効率とは物事を正しく行うことで、有効性とは正しいことを行うことである。");
		sentenceStringList.add("管理者は、高潔な品性をもってこそ、指導力を発揮し、多くの人の模範となりうる。");
		sentenceStringList
				.add("企業はなによりも”アイデア”であり、アイデアを生むことのできるのは個々の人間だけである。勇を鼓して自ら思考し、”既成観念”にあえてそむける人なくして、その企業の成長と繁栄は望めない。");
		sentenceStringList.add("２１世紀の最大の不安定化要因は人口の構造変化である。ただし、先進国における最大の問題は高齢化ではない。少子化のほうである。");
		sentenceStringList.add("全力を注がなければ、単に約束と希望があるだけで、計画はない。");
		sentenceStringList.add("効率とは、現在既に行われている事をより洗練させることである。");
		sentenceStringList.add("学ぶという事は一生続く、変化に遅れないようについていくためのプロセスだという事実を、私たちは今では受け入れている。そして、最も緊急な課題は人々に学び方を教えることである。");
		sentenceStringList.add("自らに求めるものが少なければ、成長しない。多くを自らに求めるなら、成長しない者と同程度の努力で巨人に成長できる");
		sentenceStringList.add("効果的な経営者の共通点は、ひたすらひとつの作業に集中する点にある。彼らは最も大切なことのみを行い。そのことが完了するまで、他の事に目を向けないという集中力を持っている。");
		sentenceStringList
				.add("上司は部下の仕事に責任を持つ。部下のキャリアを左右する。したがって、強みを生かすことは、成果をあげるための必要条件であるだけでなく、倫理的な至上命令、権力と地位に伴う責任である。");
		sentenceStringList.add(
				"会計学者や経営者は「純利益」という言葉を使うが、企業経営ではそんな言葉を使ってはいけない。「フューチャー・コスト（未来費用）」と呼ぶべきだ。企業というのは、リスクを負うために失敗することもある、そのときのために未来のコストを留保してあるのであって、利益の蓄積ではないのだ。");
		sentenceStringList.add("成果をあげるには、自由に使える時間を大きくまとめる必要がある。大きくまとまった時間が必要なこと、小さな時間は役に立たないことを認識しなければならない。");
		sentenceStringList
				.add("知的労働者が成果をあげるための第一歩は、実際の時間の使い方を記録することである。時間を記録する、整理する、まとめるの３段階にわたるプロセスが、成果をあげるための時間管理の基本となる。");
		sentenceStringList.add(
				"行動と動作を混同してはいけない。製品、サービス、プロセスが成果を生まなくなり、その廃棄が必要になると、あらゆる組織が組織改革に走る。もちろん、組織改革が必要なことは多い。だがそれは、何をいかに行うかという問題に取り組んだ後に行うことである。組織改革だけでは、単なる動作であって、意味ある行動の代わりとはならない。");
		sentenceStringList.add(
				"真のイノベーションと新奇さを混同してはいけない。イノベーションは価値を生む。新奇さは面白いだけである。ところが組織の多くが毎日同じことを行い、毎日同じものをつくることに飽きたというだけで、新奇なものに取り組んでしまう。");
		sentenceStringList.add(
				"現実とつじつまが合わないイノベーションを手掛けてはいけない。そのようなイノベーションが実を結ぶことは希である。たんにその新奇さのゆえに、魅力的に見えることが少なくない。しかし、それらの多くはたとえ失敗しなくとも、莫大な資金と時間を要する。");
		sentenceStringList.add("イノベーションを成功するためには、小さくスタートしなければならない。大がかりであってはならない。具体的なことだけに絞らなければならない。");
		sentenceStringList.add("イノベーションとは、論理的な分析であるとともに知覚的な認識である。");
		sentenceStringList.add(
				"イノベーションを行うためには、機会を分析することから始めなければならない。分析すべき７つの機会とは、（１）予期せぬこと（２）ギャップ（３）ニーズ（４）構造の変化（５）人口の変化（６）認識の変化（７）新知識の獲得である。");
		sentenceStringList.add("成果をあげるための秘訣をひとつだけ挙げるならば、それは集中である。成果をあげる人は、最も重要なことから始め、一度にひとつのことしかしない。");
		sentenceStringList.add(
				"集中するための第一の原則は、もはや生産的でなくなった過去のものを捨てることである。そのためには、自らの仕事と部下の仕事を定期的に見直し、「まだ行っていなかったとして、いまこれに手を付けるか」と問わなければならない。");
		sentenceStringList.add("集中が必要なのは、仕事の本質と人間の本質による。いくつかの理由はすでに明らかである。貢献を行うための時間よりも、行わなければならない貢献の方が多いからである。");
		sentenceStringList.add("成果をあげる者は、時間が制約要因であることを知っている。あらゆるプロセスにおいて、成果の限界を規定するものは、最も欠乏した資源である。それが時間である。");
		sentenceStringList
				.add("時間こそ真に普遍的な制約条件である。あらゆる仕事が時間の中で行われ、時間を費やす。それなのに、ほとんどの人が、この代替できない必要不可欠な資源を重要ではないもののように扱う。");
		sentenceStringList.add("リーダーを信頼するということは、リーダーを好きになることではない。常に同意できることでもない。リーダーの言うことが真意であると確信できることである。");
		sentenceStringList.add("自社のもっている、また引き継いできたコア・コンピタンス（中核的競争能力）、独自の専門性を尊重し、それを十二分に活かしていくように人の知恵を結集すべきだ。");
		sentenceStringList.add("企業の目的は顧客創造である以上、企業の基本的な機能はマーケティングとイノベーションの２つしかなく、そのほかはすべてコストだ。");
		sentenceStringList.add("もしマーケティングが完全に行われていたら、販売努力は不要だ。");
		sentenceStringList.add(
				"マーケティングは事業全体を完全に包含する。それは最終成果の観点、つまり顧客の視点から見たビジネスすべてである。それゆえにマーケティングに対する配慮と責任は企業全体に浸透していなければならない。");
		sentenceStringList.add("本物の変化とは人が行うことであり、一時の変化とは人が言うことである。");
		sentenceStringList.add("成功への道は、自らの手で未来をつくることによってのみ開ける。");
		sentenceStringList.add("成し遂げたいことに必要な条件を、明確に把握すればするほど、達成される確率は高まっていく。");
		sentenceStringList.add("経営者がかならず身につけておかなければいけない、大事な要素がひとつだけある。それは”品性”である。");
		sentenceStringList.add("成果をあげる人の共通点は、行わなければいけない事を、しっかり行っているというだけである。");
		sentenceStringList.add("以前にも成功をおさめたからといって、今度も以前と同様の仕事のやり方をし続ける経営者というものは、必然的に失敗する運命にある。");
		sentenceStringList.add("成果とは常に成功することではない。そこには、間違いや失敗を許す余地がなければならない。");
		sentenceStringList.add("判断の代わりに公式を使うことは、つねに間違いである。");
		sentenceStringList.add("「出来ないこと」ではなく、「出来ること」に集中しなさい。");
		sentenceStringList.add("仕事のやり方を変えるのではなく、仕事の意味を考えなさい。");
		sentenceStringList.add("まず、やりたいを決め、次に何に集中すべきかを決めなさい。");
		sentenceStringList.add("基本と原則に則っていないものは、かならず破綻する。");
		sentenceStringList.add("失敗者が何をして失敗したかよりも、成功者が何をして成功したかを学びなさい。");
		sentenceStringList.add("素晴らしくマネジメントされた組織というものは、日常はむしろ退屈な組織である。");
		sentenceStringList.add("数千のアイデアを育てて、やっと一つの成果を得ることが出来る。");
		sentenceStringList.add(
				"間違いや失敗を犯したことのない者というのは、単に無難なこと、安全なこと、つまらないことしか、やってこなかっただけである。逆に優れている者ほど、数えきれない間違いを犯すものであり、これは常に新しいことに挑戦している証拠である。");
		sentenceStringList.add("人間は、自らが望む未来の大きさに合わせて、成長する。");
		sentenceStringList.add("何かを成し遂げるためには、そのことをしか考えられないという“狂い”が必要となる。");
		sentenceStringList.add("全力を注がなければ、あなたに未来は無い。");
		sentenceStringList
				.add("成功する人に共通しているのは、ひたすらひとつの事に集中しているという点である。彼らは自分にとって一番重要なことだけに力を集中し、それが終わるまで他の事には一切手を出さない。");
		sentenceStringList.add("生産性の本質を測る真の基準は、「量」ではなくて、「質」である。");
		sentenceStringList.add("成功する企業というのは、「問題」ではなく、「チャンス」に目を向けている。");
		sentenceStringList.add("すべての偉大な成功は、地味で面倒な事の積み重ねの上に成り立っている。");
		sentenceStringList.add("複雑なものというのは、大抵うまくいかない。");
		sentenceStringList.add("事業とは何かを問われると、たいていの企業人は利益を得るための組織と答える。たいていの経済学者も同じように答える。この答えは間違いなだけではなく、的外れである。");
		sentenceStringList.add("優れた者ほど、間違いは多い。それだけ新しいことを試みるからである。");
		sentenceStringList.add(
				"社会や経済は、いかなる企業をも一夜にして消滅させる。企業は社会や経済の許しがあって存在しているのであり、有用かつ生産的な仕事をしていると見なされるかぎりにおいて、存続を許されているに過ぎない。");
		sentenceStringList.add("コミュニケーションで一番大切なことは、相手が口にしていないコトバを聞き分ける力である。");
		sentenceStringList.add("世界一になりなさい。さもなければ撤退しなさい。");
		sentenceStringList.add("組織のマネジメントとは、凄い人材を入れることや改新的なサービスを導入する事のように思われているが一番重要なのは、今ある人材と資産で何ができるかを考えることである。");
		sentenceStringList.add("成功する人間に必要な、生まれつきの能力などありはしない。ただ、あなたが成し遂げたいことに、必要な能力だけを身につければいいのだ。");
		sentenceStringList.add("どんな人でも努力すれば、“それなりの能力”は身につけることが出来る。そして、この世で成功するためには、“それなりの能力”があれば十分なのである。");
		sentenceStringList.add("砂漠では、教養など何の役にも立たない。生きる技術を持っているかどうかが生き残れるかどうかを分ける。厳しいビジネスの世界も同じである。");
		sentenceStringList.add("これまでの実績など捨てなさい。自分の強みを過信した者は、生き残れません。");
		sentenceStringList.add("生産性を上げる一番簡単な方法は、今、成果が一番出ている仕事に集中し成果が出ていない仕事からは、撤退してしまうことである。");
		sentenceStringList.add("重要なことは、明日何をするかではなく、今日、何をしたかである。");
		sentenceStringList.add("マーケティングの理想は、販売を不要にすることである。つまり、製品がおのずから売れるようにすることである。");
		sentenceStringList.add(
				"私が１３才のとき、宗教の先生が生徒一人ひとりに、「何によって人に憧れたいかね」と聞いた。誰も答えられなかった。先生は笑いながらこう言った。「いま答えられると思わない。でも５０才になって答えられないと問題だよ。人生を無駄に過ごしたことになるからね。」");
		sentenceStringList.add("寝床につくときに、翌朝起きることを楽しみにしている人間は、幸福である。");
		sentenceStringList
				.add("不得手なことの改善にあまり時間を使ってはならない。自らの強みに集中すべきである。無能を並みの水準にするには、一流を超一流にするよりも、はるかに多くのエネルギーと努力を必要とする。");
		sentenceStringList.add("２１世紀に重要視される唯一のスキルは、新しいものを学ぶスキルである。それ以外はすべて時間と共にすたれてゆく。");
		sentenceStringList.add(
				"いまさら、自分を変えようとしてはならない。うまくいくわけがない。自分の得意とする仕事のやり方を向上させることに、力を入れるべきである。人の卓越性は、ひとつの分野、あるいはわずかの分野において、実現されるのみである。");
		sentenceStringList.add("教養ある人間とは、勉強し続けなければならないことを自覚している人間のことである。");
		sentenceStringList
				.add("あらゆる活動について「もし今日これを行っていなかったとして、改めて行うか」を問わなければならない。答えが否であるなら、「それでは、いかにして一日も早くやめるか」を問わなければならない。");
		sentenceStringList.add("人材は、企業規模とは無関係である");
		sentenceStringList.add("創造性開発を叫ぶだけでは、誠に空しい");
		sentenceStringList.add("業績は、企業の内部には生じない");
		sentenceStringList.add("利益が出るのは正常の状態ではない");
		sentenceStringList.add("有能な経営者は、ほかの人々の仕事を管理するのは、一見真実だが、これはまったくの俗説なのである");
		sentenceStringList.add("この世界では何らかの仕事ができる人間はむしろ稀である");
		sentenceStringList.add("万能選手はなかなかいない");
		sentenceStringList.add("経営者の仕事は、ほかの人々を管理することから始まるものではない");
		sentenceStringList.add("効率性には3つの領域がある");
		sentenceStringList.add("経営者に必要なのはキャラクターの高潔性だ");
		sentenceStringList.add("経営は秘訣や秘伝ではない");
		sentenceStringList.add("過去から脱却せよ");
		sentenceStringList.add("我々が行動可能なのは現在であり、また未来のみである");
		sentenceStringList.add("経営者と知識労働者にとっての唯一のツールは情報である");
		sentenceStringList.add("企業にとって何よりも大事なのは、新しい生きたアイデアをどう生むかだ");
		sentenceStringList.add("イノベーションとアントルプルヌールシップは、マネジメントという規律の中枢である");

		sentenceStringList.add("CEOは、組織の中において権力を中心ではなく、責任を中心に考えるべきである");
		sentenceStringList.add("問題の明確化としっかりした定義づけができ、それによって観察可能なすべての事実を網羅しうるまでは、そうした問題の定義づけは不完全であったり、間違ったものになる");
		sentenceStringList.add("神々はすべてを見通しているから、どんなに難しくても、自分の仕事は完璧を期せよ");
		sentenceStringList.add("効率的な企業は、問題中心主義でなく、むしろ機会中心主義である");
		sentenceStringList.add("日本人の強みは、組織の構成員として、一種の家族意識を有することにある");
		sentenceStringList.add("軍隊の指揮者は、現場からのリポートに依存することなく、自分で現場へ出かけて行き、自分の目でみる");

		sentenceStringList.add("激化する国際社会。勝敗のカギは、経営者の企業家精神の確立とその発揮にある");
		sentenceStringList.add("コストも社会現象の1つである。企業経営の大きな成果は、少数の社員がもたらす");
		sentenceStringList.add("科学的経営経済学などは存在しない。業績を上げる方法は、実は誰でもが承知している");
		sentenceStringList.add("経営者が第1になすべき、また絶えず行うべき職責は、現存の資源を用いて最高の成果をあげることである");

		sentenceStringList.add("世の一般的な勧めなどは無視して、己の仕事を絞る。それがトップの[主要活動領域]である。");
		sentenceStringList.add("自分が得意だと思っていることに、溺れるな。物事の\"本質\"を鋭く透察する心を持て。");
		sentenceStringList.add("金融・財務面の要請は、企業の成長速度の4倍速く走る。だからこそ、早めに手を打つことが必要だ");
		sentenceStringList.add("やたらに危機感を煽ったり、人を無理矢理、牛馬のように駆り立てるマネジメント方式ではだめだ");
		sentenceStringList.add("階層をなす組織の危険は、上司の言うことを部下がそのまま実行することだ");
		sentenceStringList.add("最初はダメでも何度でもやり直せという態度は誤りである");
		sentenceStringList.add("リーダーは尊敬されるが、必ずしも好かれるとは限らない");
		sentenceStringList.add("半年振りに会ったドラッカー氏は語る－。“Eコマースの将来は、決してすべてがバラ色というわけではない");
		sentenceStringList.add("あらゆるビジネス誌紙が取り上げる\"トップと現場との距離\"の問題。ドラッカー氏が語ると…");
		sentenceStringList.add("市場に関する報告書など信頼してはいけない。マーケットはトップ自身の目と足で確かめよ");
		sentenceStringList.add("傲るな｡企業は､社会に存在させていただいているものだ");
		sentenceStringList.add("直属以外にも上司はいる｡そして､上司とて､斬られりゃ痛い生身の人間ということを忘れないこと");
		sentenceStringList.add("新しく生まれつつある知識労働者は､旧来の企業が主人で従業員は召使であるという考え方を今や塗り変えつつある");
		sentenceStringList.add("上司を過大評価することはあってもいいが､決して過小評価するな");
		sentenceStringList.add("道楽息子に家業を継がせるな");
		sentenceStringList.add("経営者の職務とは､働くことそのものである｡それも極めて困難で､大変な､リスクの多い仕事である");
		sentenceStringList.add("企業家の世界は物理的な世界ではない｡むしろ､価値の世界である");
		sentenceStringList.add("企業は何よりも″アイデア″であり､″アイデア″を生むことができるのは個々の人間だけである");
		sentenceStringList.add("経営管理の96％は、ルーティーン的な定例反復業務であることを、ゆめ忘れてはならない");
		sentenceStringList.add("生産性の本質を測る真の基準は量ではなくて、質である");
		sentenceStringList.add("知識労働者はボランティアとして取り扱わねばならない");
		sentenceStringList.add("最も重要なのは、タスクに目を配ることであって、自分自身についてではない");
		sentenceStringList.add("リーダーの主要な課題は、いかにして各種のバランスを上手にとるかである");
		sentenceStringList.add("すべての文明、あるいは国の中で、日本だけは、目よりも、心で接することによって理解できる国である");
		sentenceStringList.add("もし私が会社の社長だったら、一番恐れることは、大会社とその経営者が自分では露ほども不法なことをしていると考えず、道徳観念がルーズで無神経に行動することである");
		sentenceStringList.add("ｅラーニングの成否はその組立て方いかんにかかっている");
		sentenceStringList.add("『研究者に求めることが少なければ少ないほど、成果もあがる』と考えるのは、研究開発をめぐる大きな迷信にしかすぎない");
		sentenceStringList.add("ハイテクは明日の担い手であり、今日の担い手ではない");
		sentenceStringList.add("知識労働者がすべて同質のものだなどと考えたら、大間違いである");
		sentenceStringList.add("伝統的な労働力体制の下にあっては、働く人々がシステムに仕えたが、知識労働力体制の下では、システムこそが働く人々に仕えなければならない");
		sentenceStringList.add(
				"明日のエグゼクティブが学ぶべき3つの重要な事柄は、①自分で自分の面倒がみられること、②下（部下）のマネジメントではなくて、上（上司）のマネジメントをすること、③そして、経営の基本を広く着実に身につけることである");
		sentenceStringList.add("これからの乱世で組織が生き残って成功するには、自らを変革の促進者（エージェント）に変えていかねばならない");
		sentenceStringList.add("蛙の卵が池からなくならないのと同様に、アイデアは決して枯渇することはない。");

		sentenceStringList.add("世界はグローバル化と同時に、反面、次第に反対の方向、すなわち、トライバル化（部族重視）やローカル化（地域重視）の度合いも深めてきています。");
		sentenceStringList.add("企業経営のエッセンスは、何かに『卓越』することと、『決断』することである。");
		sentenceStringList.add("今日、売れている製品が明日も売れるという保証はない。企業は、絶えず明日を担う製品をつくり出さねばならない");
		sentenceStringList.add("多角化する際には“気質”（テンペラント）も勘定に入れておけ");
		sentenceStringList.add("有能さは修得できる");

		sentenceStringList.add("知識は、本の中にはない");
		sentenceStringList.add("最も重要なことから始めよ");
		sentenceStringList.add("デシジョン・メーキングにおいて、2+2=4という具合に、ドンピシャ“正しい答え”が出てくるものはデンションとはいわない");

		sentenceStringList.add("必要は発明の母ではないが、助産婦である");
		sentenceStringList.add("知識労働者自身に上下はない");
		sentenceStringList.add("人間というものは、非常に時間を消費するものであり、そして大部分の人間は時間の浪費家でもある");
		sentenceStringList.add(
				"中小企業の最高経営責任者は、他の誰にも任せることのできない次の二つの課題に取り組 時間を必ず持てるように、自分の職務を構成しなければならない。その一つは、『外部』向けの時間、すなわち、顧客、市場、技術のための時間であり、もう一つは、『内部（社内）』の基幹要員とじっくり会うための時間である。間違っても机に縛りつけられるようなことがあってはならない");

		sentenceStringList.add("革新とは、単なる方法ではなくて、新しい世界観を意味する");
		sentenceStringList.add("人間は単脳マシーンではない");
		sentenceStringList.add("学習を阻害するもの");
		sentenceStringList.add("企業が、より大きくなる必要はないが、不断に、よりよくならねばならない");
		sentenceStringList.add("問題解決を図るよりも、新しい機会に着目して創造せよ");
		sentenceStringList.add("アクション（行動）によるフォローアップ（対査）なきプロジェクト（計画）はパフォーマンス（実績）を生まない");

		sentenceStringList.add("真摯さはごまかせない");
		sentenceStringList.add("業績を上げる最大のカギは責任感である。権威や権限ではない");
		sentenceStringList.add("過去のリーダーの仕事は「命じること」だが、未来のリーダーの仕事は「聞くこと」が重要になる");
		sentenceStringList.add("何事かを成し遂げるのは、強みによってである。弱みによって何かを行うことはできない。できないことによって何かを行うことなど、到底できない");
		sentenceStringList.add("これからは、誰もが自らをマネジメントしなければならない。自らを最も貢献できる場所に置き、成長していかなければならない");
		sentenceStringList.add(
				"私の観察によれば、成果をあげる者は仕事からスタートしない。時間からスタートする。計画からもスタートしない。何に時間がとられているかを明らかにすることからスタートする。次に、時間を管理すべく自らの時間を奪おうとする非生産的な要素を退ける");
		sentenceStringList.add(
				"いかなる成果もあげられない人の方がよく働いている。成果の上がらない人は、第一に、ひとつの仕事に必要な時間を過小評価する。第二に、彼らは急ごうとする。第三に、彼らは同時にいくつかのことをしようとする");
		sentenceStringList.add("時間を管理するには、まず自らの時間をどのように使っているかを知らなければならない");

		sentenceStringList.add("誰でも自らの強みについてはよく分かっている。だが、たいていは間違っている。わかっているのはせいぜい弱みである。それさえ間違っていることが多い");
		sentenceStringList.add(
				"組織に働く者は、組織の使命が社会において重要であり、他のあらゆるものの基盤であるとの信念を持たねばならない。この信念がなければ、いかなる組織といえども、自信と誇りを失い、成果をあげる能力を失う");
		sentenceStringList.add("原因は何十年かのちに学者が明らかにするだろうが、行動する経営者としては待っていられないだろう。使えるもの、分かったことはどんどん使いなさい");
		sentenceStringList.add("急成長会社では無能な者が要職にいる。会社の成長についていけなかった人々である");
		sentenceStringList.add("経営者は、その企業の将来について、もっと時間と思索を割くべきである");
		sentenceStringList.add("幹部の仕事と知識とは、あまり関係はない");
		sentenceStringList.add("他人の短所が目につきすぎる人は、経営者には向いていない。長所を効果的に発揮させるのが自分の仕事だと考える人が、有能な経営者になれる");
		sentenceStringList.add("人々を動機付ける能力がなくては、経営者とは言えない");

		sentenceStringList.add("決断の場面においてはトップは常に孤独である");
		sentenceStringList.add("組織に働く者は、成果に何も寄与しないが無視できない仕事に時間をとられる。膨大な時間が、ほとんど役に立たない仕事、あるいはまったく役に立たない仕事に費やされている");
		sentenceStringList.add("効率とは物事を正しく行うことで、有効性とは正しいことを行うことである");
		sentenceStringList.add("管理者は高潔な品性をもってこそ、指導力を発揮し、多くの人の模範となりうる");
		sentenceStringList
				.add("企業はなによりも”アイデア”であり、アイデアを生むことのできるのは個々の人間だけである。勇を鼓して自ら思考し、”既成観念”にあえてそむける人なくして、その企業の成長と繁栄は望めない");
		sentenceStringList.add("２１世紀の最大の不安定化要因は人口の構造変化である。ただし、先進国における最大の問題は高齢化ではない。少子化のほうである");
		sentenceStringList.add("全力を注がなければ、単に約束と希望があるだけで、計画はない");
		sentenceStringList.add("学ぶという事は一生続く、変化に遅れないようについていくためのプロセスだという事実を、私たちは今では受け入れている。そして、最も緊急な課題は人々に学び方を教えることである");

		sentenceStringList.add("効率とは、現在既に行われている事をより洗練させることである");
		sentenceStringList.add("自らに求めるものが少なければ、成長しない。多くを自らに求めるなら、成長しない者と同程度の努力で巨人に成長できる");
		sentenceStringList.add("効果的な経営者の共通点は、ひたすらひとつの作業に集中する点にある。彼らは最も大切なことのみを行い。そのことが完了するまで、他の事に目を向けないという集中力を持っている");
		sentenceStringList.add("ピータードラッカー");
		sentenceStringList.add("イノベーションを成功するためには、小さくスタートしなければならない。大がかりであってはならない。具体的なことだけに絞らなければならない");
		sentenceStringList.add("" + "");
		sentenceStringList.add("成果をあげるための秘訣をひとつだけ挙げるならば、それは集中である");
		sentenceStringList.add(
				"集中するための第一の原則は、もはや生産的でなくなった過去のものを捨てることである。そのためには、自らの仕事と部下の仕事を定期的に見直し、「まだ行っていなかったとして、いまこれに手を付けるか」と問わなければならない");

		sentenceStringList.add("集中が必要なのは、仕事の本質と人間の本質による。いくつかの理由はすでに明らかである。貢献を行うための時間よりも、行わなければならない貢献の方が多いからである");
		sentenceStringList.add("成果をあげる者は、時間が制約要因であることを知っている。あらゆるプロセスにおいて、成果の限界を規定するものは、最も欠乏した資源である。それが時間である");
		sentenceStringList
				.add("時間こそ真に普遍的な制約条件である。あらゆる仕事が時間の中で行われ、時間を費やす。それなのに、ほとんどの人が、この代替できない必要不可欠な資源を重要ではないもののように扱う。");
		sentenceStringList.add("企業の目的は顧客創造である以上、企業の基本的な機能はマーケティングとイノベーションの２つしかなく、そのほかはすべてコストだ");
		sentenceStringList.add("もしマーケティングが完全に行われていたら、販売努力は不要だ");
		sentenceStringList
				.add("マーケティングは事業全体を完全に包含する。それは最終成果の観点、つまり顧客の視点から見たビジネスすべてである。それゆえにマーケティングに対する配慮と責任は企業全体に浸透していなければならない");
		sentenceStringList.add(
				"現実とつじつまが合わないイノベーションを手掛けてはいけない。そのようなイノベーションが実を結ぶことは希である。たんにその新奇さのゆえに、魅力的に見えることが少なくない。しかし、それらの多くはたとえ失敗しなくとも、莫大な資金と時間を要する");
		sentenceStringList.add(
				"会計学者や経営者は「純利益」という言葉を使うが、企業経営ではそんな言葉を使ってはいけない。「フューチャー・コスト（未来費用）」と呼ぶべきだ。企業というのは、リスクを負うために失敗することもある、そのときのために未来のコストを留保してあるのであって、利益の蓄積ではないのだ");

		sentenceStringList.add(
				"真のイノベーションと新奇さを混同してはいけない。イノベーションは価値を生む。新奇さは面白いだけである。ところが組織の多くが毎日同じことを行い、毎日同じものをつくることに飽きたというだけで、新奇なものに取り組んでしまう");
		sentenceStringList.add("選択肢を前にした若者が答えるべき問題は、正確には、何をしたらよいかではなく、自分を使って何をしたいかである");
		sentenceStringList.add("チャンスとは一つのことに心に集中することによって、かろうじて見つけることができるものである");
		sentenceStringList.add("傲(おご)ってはいけません。企業は、社会によって存在させてもらっている存在なのです");
		sentenceStringList.add("最も重要なことから始めなさい");
		sentenceStringList.add("未来を予知しようとすることは、夜中に田舎道をライトもつけずに走りながら、後ろの窓から外を見るようなものである");
		sentenceStringList.add("一番確実な未来予知の方法は、未来自体を作り出してしまうことである");
		sentenceStringList.add("寝床につくときに、翌朝起きることを楽しみにしている人間は、幸福である");

		sentenceStringList.add("我々が行動可能なのは現在であり、また未来のみである");
		sentenceStringList.add("２１世紀に重要視される唯一のスキルは、新しいものを学ぶスキルである。それ以外はすべて時間と共にすたれてゆく");
		sentenceStringList.add("いまさら、自分を変えようとしてはならない。そんなのは、うまくいくわけがない");
		sentenceStringList.add("人の卓越性は、ひとつの分野、あるいはわずかの分野においてのみ、実現されるのである");
		sentenceStringList.add("教養ある人間は、勉強し続けなければならないということを自覚している");
		sentenceStringList.add("不得手なことの改善にあまり時間を使ってはならない。自らの強みに集中すべきである");
		sentenceStringList.add("無能を並みの水準にするには一流を超一流にするよりも、はるかに多くのエネルギーと努力を必要とする");
		sentenceStringList
				.add("あらゆる活動について「もし今日これを行っていなかったとして、 改めて行うか」を問わなければならない。答えが否であるなら、「それでは、いかにして一日も早くやめるか」を問わなければならない");
		sentenceStringList.add("コミュニケーションで一番大切なことは、相手が口にしていない言葉を、聞き分ける力である");
		sentenceStringList.add("世界一になりなさい。さもなければ撤退しなさい");
		sentenceStringList.add("組織のマネジメントとは、凄い人材を入れることや改新的なサービスを導入する事のように思われているが、一番重要なのは、今ある人材と資産で何ができるかを考えることである");
		sentenceStringList.add("");
		sentenceStringList.add("成功する人間に必要な生まれつきの能力などありはしない。ただ、あなたが成し遂げたいことに、必要な能力だけを身につければいいのだ");
		sentenceStringList.add("");
		sentenceStringList.add("どんな人でも努力すれば、“それなりの能力”は身につけることが出来る。そして、この世で成功するためには、“それなりの能力”があれば十分なのである");
		sentenceStringList.add("組織は常に進化していなくてはならない");
		sentenceStringList.add("砂漠では、教養など何の役にも立たない。生きる技術を持っているかどうかが生き残れるかどうかを分ける。厳しいビジネスの世界も同じである");
		sentenceStringList.add("組織の活動というのは、「いかに世の中に貢献していくか」という一点に集約される");
		sentenceStringList.add("重要なことは明日何をするかではなく、今日、何をしたかである");
		sentenceStringList.add("");
		sentenceStringList.add("生産性を上げる一番簡単な方法は、今、一番成果が出ている仕事に集中し、成果が出ていない仕事からは、撤退してしまうことである");
		sentenceStringList.add("新しい事業をつくり出すときは、大きなビジョンである必要はない。しかし、今日の常識とは違うものでなければならない");
		sentenceStringList.add("マーケティングの理想は販売を不要にすることである。つまり、製品がおのずから売れるようにすることである");
		sentenceStringList.add("");
		sentenceStringList.add("市場において目指すべき地位は、最大ではなく、最適である");
		sentenceStringList.add("生産性を向上させるためにまず問うべきは、何が目的か、何を実現させようとしているか、なぜそれを行うかである");
		sentenceStringList.add("");
		sentenceStringList.add("さらに、たえず検証されなければならない");
		sentenceStringList.add("そして、それらの条件が互いに合致していること。");
		sentenceStringList.add("そして、周知徹底されなければならない。");
		sentenceStringList.add("まず経営環境、使命、強みが現実と一致していること。");
		sentenceStringList.add("事業を行うときは、次の４つの条件を満たしておかなければならない。");
		sentenceStringList.add("人間は自らが望む未来の大きさに合わせて、成長する");
		sentenceStringList.add("現状を把握しなければ未来は語れない");
		sentenceStringList.add("");
		sentenceStringList.add("何かを成し遂げるためには、そのことをしか考えられないという“狂い”が必要となる");
		sentenceStringList.add("計画とは未来に関する現在の決定である-");
		sentenceStringList.add("成功する人に共通しているのは、ひたすらひとつの事に集中しているという点である");
		sentenceStringList.add("");
		sentenceStringList.add("生産性の本質を測る真の基準は「量」ではなく、「質」である");
		sentenceStringList.add("成功する企業というのは、「問題」ではなく、「チャンス」に目を向けている");
		sentenceStringList.add("すべての偉大な成功は、地味で面倒な事の積み重ねの上に成り立っている");
		sentenceStringList.add("優れた者ほど間違いは多い。それだけ新しいことを試みるからである");
		sentenceStringList.add("正しい構造が成果を約束してくれるわけではない。しかし、間違った構造は成果を生まず、最高の努力を無駄にしてしまう");
		sentenceStringList.add("「出来ないこと」ではなく、「出来ること」に集中しなさい");
		sentenceStringList.add("仕事のやり方を変えるのではなく、仕事の意味を考えなさい");
		sentenceStringList.add("まず、やりたいを決め、次に何に集中すべきかを決めなさい");
		sentenceStringList.add("基本と原則に則っていないものは、かならず破綻する");
		sentenceStringList.add("人間は、「自分でなければ出来ない」と錯覚していることが多すぎる失敗者が何をして失敗したかよりも、成功者が何をして成功したかを学びなさい");
		sentenceStringList.add("理論というものは現実に従って変化していく");
		sentenceStringList.add("素晴らしくマネジメントされた組織というものは、日常はむしろ退屈な組織である");
		sentenceStringList.add("数千のアイデアを育てて、やっと一つの成果を得ることが出来る");
		sentenceStringList.add(
				"間違いや失敗を犯したことのない者というのは、単に無難なこと、安全なこと、つまらないことしか、やってこなかっただけである。逆に優れている者ほど、数えきれない間違いを犯すものであり、これは常に新しいことに挑戦している証拠である");
		sentenceStringList.add("成功への道は自らの手で未来をつくることによってのみ開ける");
		sentenceStringList.add("成し遂げたいことに必要な条件を、明確に把握すればするほど、達成される確率は高まっていく");
		sentenceStringList.add("経営者がかならず身につけておかなければいけない、大事な要素がひとつだけある。それは”品性”である");
		sentenceStringList.add("成果をあげる人の共通点は、行わなければいけない事を、しっかり行っているというだけである");
		sentenceStringList.add("優れた医者というのは、正しい診断を最も多く下す人ではない。誤った診断をすばやく見つけ、それを直ちに改めることのできる人である");
		sentenceStringList
				.add("学問的な言い方ではないが、仕事ができる組織は仕事を楽しんでいる以前にも成功をおさめたからといって、今度も以前と同様の仕事のやり方をし続ける経営者というものは必然的に失敗する運命にある");
		sentenceStringList.add("経営者は常に現実的でなければならない");
		sentenceStringList.add("成果とは常に成功することではない。そこには間違いや失敗を許す余地がなければならない");
		sentenceStringList.add("判断の代わりに公式を使うことは、つねに間違いである");
		sentenceStringList.add("誰かが勇気ある決断をしなければ、どんな事業も成功しないだろう働く人たちの姿勢は、何にもまして経営管理者の行動を反映する。彼らの姿勢は、経営管理者の能力と構造を映す");
		sentenceStringList.add("真のマーケティングは顧客から出発する。すなわち人間、現実、欲求、価値から出発する");
		sentenceStringList.add("一つの成果を得る為には数千のアイデアを育てなければならない");
		sentenceStringList.add("たいていの経営者は、その時間の大半を過ぎ去った「きのう」の諸問題に費やしている後継者を自分一人で選んではならない。どうしても20年前の自分に似た者を選びたくなる");
		sentenceStringList.add("内を見るよりも外を見るほうが易しい。しかもそのほうが賢い");
		sentenceStringList.add("明日は必ず来る。そして、明日は今日とは違う");
		sentenceStringList.add("人間にとって成長ないし発展とは、何に対して貢献すべきかを自らが決定できるようになることである");
		sentenceStringList.add("リスクには２種類ある。踏むには危険が大きすぎるリスク。それと逃すにはあまりにも惜しいリスクだ");
		sentenceStringList.add("顧客にとっての価値を想像してはならない。直に聞かなければならない");
		sentenceStringList.add("部下の成長は、育成した者にとって昇進に値する貢献としなければならない");
		sentenceStringList.add(
				"果をあげる人とあげない人の差は才能ではない。いくつかの習慣的な姿勢と、基礎的な方法を身につけているかどうかの問題である。しかし、組織というものが最近の発明であるために、人はまだこれらのことに優れるに至っていない");
		sentenceStringList.add("おそらく今日、高等教育を受けた人の割合が世界でもっとも多い国が日本である。 日本にとっての問題は、いかにして彼ら高等教育を受けた人たちを生産的な存在にするかである");
		sentenceStringList.add("リーダーは尊敬されるが、必ずしも好かれるとは限らない");
		sentenceStringList.add("コミュニケーションで最も大切なことは、相手の言わない本音の部分を聞くことである");
		sentenceStringList.add("マネジメントたる者は、共に働く者から自らの仕事を教わらなければならない");
		sentenceStringList.add("上司を過大評価することはあってもいいが､決して過小評価するな");
		sentenceStringList.add("非合理的な顧客なるものは存在しない。顧客は、顧客にとっての現実にもとづいて合理的に行動している");
		sentenceStringList.add("指揮者は、一人の人間を受け入れるために楽譜を書き直したりはしない");
		sentenceStringList.add("日本人の強みは、組織の構成員として、一種の「家族意識」を有することにある");
		sentenceStringList.add("自らの果たすべき貢献は何かという問いからスタートするとき、人は自由となる。 責任をもつがゆえに、自由となる");
		sentenceStringList.add("喜びは成果の中になければならない。石臼に向かいながらも丘の上を見なければならない");
		sentenceStringList.add("完璧な青写真なるものは、二重に人を欺く。それは、問題を解決できないだけでなく、問題を隠すことによって、本当の解決を難しくする");
		sentenceStringList.add("問題の分析によって解決案が一つしか見つからなければ、その解決案は先入観に理屈をつけたにすぎないものと疑うべきである");
		sentenceStringList.add("問題の分析によって解決案が一つしか見つからなければ、その解決案は先入観に理屈をつけたにすぎないものと疑うべきである");
		sentenceStringList.add("ビジネスには二つの機能しかない。マーケティングとイノベーションである");
		sentenceStringList.add("最初はダメでも何度でもやり直せという態度は誤りである");
		sentenceStringList.add(
				"歴史上いかなる国においても、企業とくに大企業は株主のためにのみマネジメントすべきであるという主張はもちろん、 主として株主のためにマネジメントすべきであるという主張さえ、主流になったことはない");
		sentenceStringList.add("忠誠心を買うことはできない。獲得すべきものである。金の力で引き留めようとすれば、引き留められた者が誘惑に対する自分の弱さを会社のせいにするだけである");
		sentenceStringList.add("複雑なものはうまくいかない");
		sentenceStringList.add("昨日を守ること、すなわちイノベーションを行わないことのほうが明日をつくることよりも大きなリスクを伴う");
		sentenceStringList.add("強みの上に築け！");
		sentenceStringList.add("表の風に吹かれろ！");
		sentenceStringList.add("予期せざる成功・失敗にこそ革新への源が");
		sentenceStringList.add("自分はここで何を貢献できるかを考えよ");
		sentenceStringList.add("昨日を捨てよ");
		sentenceStringList.add("ＮＩＨ（お山の大将）根性を捨てよ");
		sentenceStringList.add("物事は、人が思ったり、言ったりすることの２倍かかる");
		sentenceStringList.add("知識は消え去りやすい");
		sentenceStringList.add("中小企業が〝巨人〟に鵜呑みにされて消滅されるなんていうのは全くのナンセンスである");

		for (String text : sentenceStringList) {
			Sentence sentence = new Sentence();

			sentence.setSentence(text);
			sentence.setLanguage("jp");
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

	@RequestMapping(value = "/uploadSentence", method = RequestMethod.GET)
	public void uploadSentenceFile() {
		try {

			/*******************************************/
			String line = "";

			String csvFile = "/opt/apache-tomcat-8.5.29/";
			BufferedReader br = null;
			FileReader fr = null;
			fr = new FileReader(csvFile);
			br = new BufferedReader(fr);
			br.readLine();
			logger.info("----------START---uploadSentenceFile--------");
			int i = 1;

			while ((line = br.readLine()) != null) {
				logger.info("----------COMULM NUM----------- '{}'", i);
				i = i + 1;
				String sentence = null;

				String[] column = line.split(",");

				logger.info("--column[3]-sourceSentence--");

				if (column[3] != null && !column[3].equals("")) {
					sentence = column[3];
					logger.info("/******** 3 ***sentence******* '{}'", sentence);
					Sentence sentenceToAdd = new Sentence();

					sentenceToAdd.setSentence(sentence);
					sentenceToAdd.setLanguage("jp");
					sentenceRepository.save(sentenceToAdd);

					CloodApiFunction CloudApi = new CloodApiFunction();
					List<Entity> sentenceEntities = new ArrayList<>();
					try {
						sentenceEntities = CloudApi.analyzeEntitiesText(sentence);
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
						sentenceEntityRelationPK.setIdSentence(sentenceToAdd.getIdSentence());

						SentenceEntityRelation sentenceEntityRelation = new SentenceEntityRelation();
						sentenceEntityRelation.setSentenceRelationPK(sentenceEntityRelationPK);
						sentenceEntityRelation.setEntity(entityDb);
						sentenceEntityRelation.setSentence(sentenceToAdd);
						sentenceEntityRelationRepository.save(sentenceEntityRelation);
					}

				}
			}
			br.close();
			logger.info("---SENTENCE-------CLOSE--FINISH---UPLOAD------");

		} catch (Exception e) {
			logger.error("---------EXCEPTION UPLOAD SENTENCE ----------------", e);
			logger.info("----SENTENCE------CLOSE-----Exception------");
		}

	}
}
