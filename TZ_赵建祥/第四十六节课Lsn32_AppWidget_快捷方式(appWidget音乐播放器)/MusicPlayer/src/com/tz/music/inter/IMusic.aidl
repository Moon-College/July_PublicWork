package com.tz.music.inter;
import com.tz.music.inter.bean.Music;

interface IMusic {
	/**
	 * 播放
	 */
	Music play();

	/**
	 * 停止，再点击播放时重新播放
	 */
	void stop();

	/**
	 * 暂停，再点击播放时接着播放
	 */
	void pause();
	
	/**
	 * 下一首
	 * @param direction   方向  0上一首 1下一首
	 */
	void next(int direction);
	
	/**
	 * 设置播放模式
	 * @param playModel 	0 顺序  1 随机 2 循环 3 单曲
	 */
	void setPlayModel(int playModel);
}
