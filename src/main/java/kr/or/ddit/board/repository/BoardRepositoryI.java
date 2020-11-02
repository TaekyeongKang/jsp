package kr.or.ddit.board.repository;

import kr.or.ddit.board.model.BoardVO;

public interface BoardRepositoryI {
	BoardVO getBoard(int boardNo);
}
