from win10toast import ToastNotifier  # 导入 ToastNotifier 类，用于显示 Windows 10 的通知
import random  # 导入 random 模块，用于随机选择格言
import time  # 导入 time 模块，用于添加延迟

def notify(random_quote):
    toast = ToastNotifier()  # 创建 ToastNotifier 对象
    toast.show_toast("该喝水了！", "请保护好自己的眼睛！", icon_path=None, duration=5)  # 显示水杯图标的通知
    toast.show_toast("人生格言", random_quote, duration=6, threaded=True)  # 显示格言的通知，并使用线程处理
    while toast.notification_active():  # 当通知仍然活动时
        time.sleep(0.1)  # 等待一小段时间

if __name__ == "__main__":
    quotes_list = []  # 创建一个空列表，用于存储格言
    with open('quotes.txt', encoding='utf-8') as f:  # 打开格言文件
        for line in f:  # 遍历文件的每一行
            if line.strip():  # 如果该行不为空
                quotes_list.append(line.strip())  # 将去除首尾空格的行添加到格言列表中
    while True:  # 无限循环
        rand_item = random.choice(quotes_list)  # 从格言列表中随机选择一条格言
        notify(rand_item)  # 调用 notify 函数，显示格言通知
        time.sleep(5)  # 等待一段时间后继续下一次循环


