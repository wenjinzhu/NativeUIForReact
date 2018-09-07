# NativeUIForReact
React Native：原生UI组件封装，RoundImageView，可配置圆形图片、矩形圆角图片和椭圆形图片
# 可配置属性
1. name：本地资源文件的文件名
2. type：显示的图片类型（circle/round/oval）
3. roundradius：圆角的角度大小
# js中使用
```
export default class RoundImageViewTest extends Component {
  render() {
    return (
      <View style={styles.container}>
        <RoundImageView style={{width: 100, height: 100}} name="icon_rectangle" type="circle" />
		    <RoundImageView style={{width: 100, height: 100}} name="icon_rectangle" type="round" roundradius={30} />
		    <RoundImageView style={{width: 80, height: 100}} name="icon_rectangle" type="oval" roundradius={45} />
      </View>
    );
  }
}
```
# 效果图
[效果图](https://github.com/wenjinzhu/NativeUIForReact/blob/master/%E6%95%88%E6%9E%9C%E5%9B%BE.jpg)
