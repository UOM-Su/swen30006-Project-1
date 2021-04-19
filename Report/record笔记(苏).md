1. charger有两种费用service fee（即lookup fee），还有种activity fee，之所以不能把这两种都写在charger里，是因为如果以后要增加什么费用，比如说delete fee，你就要去改charger的代码，要改太多，为了以后系统的维护，所以要把两个·fee都提出来了，这叫做low coupling
2. mail pool里面的item，实现了一种protected variation，如果要改变mailitem的一些新的东西或者提取（比如说get destination floor），只需要改变inner class里的代码，而不需要改变整个
3. 原来的robot只能装两个物品，为了以后增加新的机器人并方便维护代码，比如说将来需要有个机器人可以装三个东西，直接继承abstract robot就行，这用到了polynomial概念
4. charger的前两个variable设置成是final，因为每个look up的费用是0.1是固定的，每上或下一层楼的费用也是固定，然后第三个和第四个variable不设置成final的原因是因为pdf中提到说activity unit price是configurable-->未来会改变，Markup percentage也是subject change configurable，未来会根据需求改变
5. feeFineder就是wifi接收器，如果把这个class直接写在simulation里就违反了indirection，因为这个class是用来调用modem里的func，万一以后这个wifi坏了或者被人攻击了，整个simulation就会受到影响
6. statisticRecorder是用来计算整个simulation的数据，之所以要创建这个class是因为以后要是需要统计更多的东西，只需要在这里面改不用去别的地方改，保持了high cohesion
7. charger现实中不会存在，引用了pure fabrication
8. generator通过mail factory创建，避免了直接耦合，以便更好的维护
9. robot到了当前楼层，说明马上要送到了，然后你要去将service fee，movement fee都加进去，我们是将robot的回程费用也加进去了![image-20210419095916220](C:\Users\suhon\AppData\Roaming\Typora\typora-user-images\image-20210419095916220.png)
10. PropertiesReader这是用来提取文件

