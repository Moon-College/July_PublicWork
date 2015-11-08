package com.tz.music.inter;
import com.tz.music.inter.bean.Music;

interface IMusic {
	/**
	 * ����
	 */
	Music play();

	/**
	 * ֹͣ���ٵ������ʱ���²���
	 */
	void stop();

	/**
	 * ��ͣ���ٵ������ʱ���Ų���
	 */
	void pause();
	
	/**
	 * ��һ��
	 * @param direction   ����  0��һ�� 1��һ��
	 */
	void next(int direction);
	
	/**
	 * ���ò���ģʽ
	 * @param playModel 	0 ˳��  1 ��� 2 ѭ�� 3 ����
	 */
	void setPlayModel(int playModel);
}
