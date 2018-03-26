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

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		sentenceStringList.add("真摯さはごまかせない");
		sentenceStringList.add("業績を上げる最大のカギは責任感である。権威や権限ではない");
		sentenceStringList.add("過去のリーダーの仕事は「命じること」だが、未来のリーダーの仕事は「聞くこと」が重要になる");
		sentenceStringList.add("何事かを成し遂げるのは、強みによってである。弱みによって何かを行うことはできない。できないことによって何かを行うことなど、到底できない");
		sentenceStringList.add("これからは、誰もが自らをマネジメントしなければならない。自らを最も貢献できる場所に置き、成長していかなければならない");
		sentenceStringList.add("私の観察によれば、成果をあげる者は仕事からスタートしない。時間からスタートする。計画からもスタートしない。何に時間がとられているかを明らかにすることからスタートする。次に、時間を管理すべく自らの時間を奪おうとする非生産的な要素を退ける");
		sentenceStringList.add("いかなる成果もあげられない人の方がよく働いている。成果の上がらない人は、第一に、ひとつの仕事に必要な時間を過小評価する。第二に、彼らは急ごうとする。第三に、彼らは同時にいくつかのことをしようとする");
		sentenceStringList.add("時間を管理するには、まず自らの時間をどのように使っているかを知らなければならない");

		sentenceStringList.add("誰でも自らの強みについてはよく分かっている。だが、たいていは間違っている。わかっているのはせいぜい弱みである。それさえ間違っていることが多い");
		sentenceStringList.add("組織に働く者は、組織の使命が社会において重要であり、他のあらゆるものの基盤であるとの信念を持たねばならない。この信念がなければ、いかなる組織といえども、自信と誇りを失い、成果をあげる能力を失う");
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
		sentenceStringList.add("企業はなによりも”アイデア”であり、アイデアを生むことのできるのは個々の人間だけである。勇を鼓して自ら思考し、”既成観念”にあえてそむける人なくして、その企業の成長と繁栄は望めない");
		sentenceStringList.add("２１世紀の最大の不安定化要因は人口の構造変化である。ただし、先進国における最大の問題は高齢化ではない。少子化のほうである");
		sentenceStringList.add("全力を注がなければ、単に約束と希望があるだけで、計画はない");
		sentenceStringList.add("学ぶという事は一生続く、変化に遅れないようについていくためのプロセスだという事実を、私たちは今では受け入れている。そして、最も緊急な課題は人々に学び方を教えることである");

		sentenceStringList.add("効率とは、現在既に行われている事をより洗練させることである");
		sentenceStringList.add("自らに求めるものが少なければ、成長しない。多くを自らに求めるなら、成長しない者と同程度の努力で巨人に成長できる");
		sentenceStringList.add("効果的な経営者の共通点は、ひたすらひとつの作業に集中する点にある。彼らは最も大切なことのみを行い。そのことが完了するまで、他の事に目を向けないという集中力を持っている");
		sentenceStringList.add("ピータードラッカー");
		sentenceStringList.add("イノベーションを成功するためには、小さくスタートしなければならない。大がかりであってはならない。具体的なことだけに絞らなければならない");
		sentenceStringList.add(""
				+ "");
		sentenceStringList.add("成果をあげるための秘訣をひとつだけ挙げるならば、それは集中である");
		sentenceStringList.add("集中するための第一の原則は、もはや生産的でなくなった過去のものを捨てることである。そのためには、自らの仕事と部下の仕事を定期的に見直し、「まだ行っていなかったとして、いまこれに手を付けるか」と問わなければならない");

		sentenceStringList.add("集中が必要なのは、仕事の本質と人間の本質による。いくつかの理由はすでに明らかである。貢献を行うための時間よりも、行わなければならない貢献の方が多いからである");
		sentenceStringList.add("成果をあげる者は、時間が制約要因であることを知っている。あらゆるプロセスにおいて、成果の限界を規定するものは、最も欠乏した資源である。それが時間である");
		sentenceStringList.add("時間こそ真に普遍的な制約条件である。あらゆる仕事が時間の中で行われ、時間を費やす。それなのに、ほとんどの人が、この代替できない必要不可欠な資源を重要ではないもののように扱う。");
		sentenceStringList.add("企業の目的は顧客創造である以上、企業の基本的な機能はマーケティングとイノベーションの２つしかなく、そのほかはすべてコストだ");
		sentenceStringList.add("もしマーケティングが完全に行われていたら、販売努力は不要だ");
		sentenceStringList.add("マーケティングは事業全体を完全に包含する。それは最終成果の観点、つまり顧客の視点から見たビジネスすべてである。それゆえにマーケティングに対する配慮と責任は企業全体に浸透していなければならない");
		sentenceStringList.add("現実とつじつまが合わないイノベーションを手掛けてはいけない。そのようなイノベーションが実を結ぶことは希である。たんにその新奇さのゆえに、魅力的に見えることが少なくない。しかし、それらの多くはたとえ失敗しなくとも、莫大な資金と時間を要する");
		sentenceStringList.add("会計学者や経営者は「純利益」という言葉を使うが、企業経営ではそんな言葉を使ってはいけない。「フューチャー・コスト（未来費用）」と呼ぶべきだ。企業というのは、リスクを負うために失敗することもある、そのときのために未来のコストを留保してあるのであって、利益の蓄積ではないのだ");

		sentenceStringList.add("真のイノベーションと新奇さを混同してはいけない。イノベーションは価値を生む。新奇さは面白いだけである。ところが組織の多くが毎日同じことを行い、毎日同じものをつくることに飽きたというだけで、新奇なものに取り組んでしまう");
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
		sentenceStringList.add("あらゆる活動について「もし今日これを行っていなかったとして、 改めて行うか」を問わなければならない。答えが否であるなら、「それでは、いかにして一日も早くやめるか」を問わなければならない");
		sentenceStringList.add("コミュニケーションで一番大切なことは、相手が口にしていない言葉を、聞き分ける力である");
		sentenceStringList.add("世界一になりなさい。さもなければ撤退しなさい");
		sentenceStringList.add("組織のマネジメントとは、凄い人材を入れることや改新的なサービスを導入する事のように思われているが、一番重要なのは、今ある人材と資産で何ができるかを考えることである");sentenceStringList.add("");
		sentenceStringList.add("成功する人間に必要な生まれつきの能力などありはしない。ただ、あなたが成し遂げたいことに、必要な能力だけを身につければいいのだ");
		sentenceStringList.add("");sentenceStringList.add("どんな人でも努力すれば、“それなりの能力”は身につけることが出来る。そして、この世で成功するためには、“それなりの能力”があれば十分なのである");
		sentenceStringList.add("組織は常に進化していなくてはならない");
		sentenceStringList.add("砂漠では、教養など何の役にも立たない。生きる技術を持っているかどうかが生き残れるかどうかを分ける。厳しいビジネスの世界も同じである");sentenceStringList.add("組織の活動というのは、「いかに世の中に貢献していくか」という一点に集約される");
		sentenceStringList.add("重要なことは明日何をするかではなく、今日、何をしたかである");
		sentenceStringList.add("");sentenceStringList.add("生産性を上げる一番簡単な方法は、今、一番成果が出ている仕事に集中し、成果が出ていない仕事からは、撤退してしまうことである");
		sentenceStringList.add("新しい事業をつくり出すときは、大きなビジョンである必要はない。しかし、今日の常識とは違うものでなければならない");
		sentenceStringList.add("マーケティングの理想は販売を不要にすることである。つまり、製品がおのずから売れるようにすることである");sentenceStringList.add("");
		sentenceStringList.add("市場において目指すべき地位は、最大ではなく、最適である");
		sentenceStringList.add("生産性を向上させるためにまず問うべきは、何が目的か、何を実現させようとしているか、なぜそれを行うかである");sentenceStringList.add("");
		sentenceStringList.add("さらに、たえず検証されなければならない");
		sentenceStringList.add("そして、それらの条件が互いに合致していること。");sentenceStringList.add("そして、周知徹底されなければならない。");
		sentenceStringList.add("まず経営環境、使命、強みが現実と一致していること。");
		sentenceStringList.add("事業を行うときは、次の４つの条件を満たしておかなければならない。");sentenceStringList.add("人間は自らが望む未来の大きさに合わせて、成長する");
		sentenceStringList.add("現状を把握しなければ未来は語れない");
		sentenceStringList.add("");sentenceStringList.add("何かを成し遂げるためには、そのことをしか考えられないという“狂い”が必要となる");
		sentenceStringList.add("計画とは未来に関する現在の決定である-");
		sentenceStringList.add("成功する人に共通しているのは、ひたすらひとつの事に集中しているという点である");sentenceStringList.add("");
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
		sentenceStringList.add("間違いや失敗を犯したことのない者というのは、単に無難なこと、安全なこと、つまらないことしか、やってこなかっただけである。逆に優れている者ほど、数えきれない間違いを犯すものであり、これは常に新しいことに挑戦している証拠である");
		sentenceStringList.add("成功への道は自らの手で未来をつくることによってのみ開ける");
		sentenceStringList.add("成し遂げたいことに必要な条件を、明確に把握すればするほど、達成される確率は高まっていく");
		sentenceStringList.add("経営者がかならず身につけておかなければいけない、大事な要素がひとつだけある。それは”品性”である");
		sentenceStringList.add("成果をあげる人の共通点は、行わなければいけない事を、しっかり行っているというだけである");
		sentenceStringList.add("優れた医者というのは、正しい診断を最も多く下す人ではない。誤った診断をすばやく見つけ、それを直ちに改めることのできる人である");
		sentenceStringList.add("学問的な言い方ではないが、仕事ができる組織は仕事を楽しんでいる以前にも成功をおさめたからといって、今度も以前と同様の仕事のやり方をし続ける経営者というものは必然的に失敗する運命にある");
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
		sentenceStringList.add("果をあげる人とあげない人の差は才能ではない。いくつかの習慣的な姿勢と、基礎的な方法を身につけているかどうかの問題である。しかし、組織というものが最近の発明であるために、人はまだこれらのことに優れるに至っていない");sentenceStringList.add("おそらく今日、高等教育を受けた人の割合が世界でもっとも多い国が日本である。 日本にとっての問題は、いかにして彼ら高等教育を受けた人たちを生産的な存在にするかである");
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
		sentenceStringList.add("問題の分析によって解決案が一つしか見つからなければ、その解決案は先入観に理屈をつけたにすぎないものと疑うべきである");sentenceStringList.add("問題の分析によって解決案が一つしか見つからなければ、その解決案は先入観に理屈をつけたにすぎないものと疑うべきである");
		sentenceStringList.add("ビジネスには二つの機能しかない。マーケティングとイノベーションである");
		sentenceStringList.add("最初はダメでも何度でもやり直せという態度は誤りである");
		sentenceStringList.add("歴史上いかなる国においても、企業とくに大企業は株主のためにのみマネジメントすべきであるという主張はもちろん、 主として株主のためにマネジメントすべきであるという主張さえ、主流になったことはない");
		sentenceStringList.add("忠誠心を買うことはできない。獲得すべきものである。金の力で引き留めようとすれば、引き留められた者が誘惑に対する自分の弱さを会社のせいにするだけである");
		sentenceStringList.add("複雑なものはうまくいかない");
		sentenceStringList.add("昨日を守ること、すなわちイノベーションを行わないことのほうが明日をつくることよりも大きなリスクを伴う");
		sentenceStringList.add("強みの上に築け！");
		sentenceStringList.add("表の風に吹かれろ！");
		sentenceStringList.add("予期せざる成功・失敗にこそ革新への源が");
		sentenceStringList.add("自分はここで何を貢献できるかを考えよ");
		sentenceStringList.add("昨日を捨てよ");sentenceStringList.add("ＮＩＨ（お山の大将）根性を捨てよ");
		sentenceStringList.add("物事は、人が思ったり、言ったりすることの２倍かかる");
		sentenceStringList.add("知識は消え去りやすい");
		sentenceStringList.add("中小企業が〝巨人〟に鵜呑みにされて消滅されるなんていうのは全くのナンセンスである");


		
		
		
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
