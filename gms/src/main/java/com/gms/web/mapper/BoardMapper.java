package com.gms.web.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gms.web.domain.ArticleDTO;
import com.gms.web.domain.MemberDTO;
@Repository
public interface BoardMapper {
    public void insert(ArticleDTO m) ;
    public List<?> selectList(Map<?, ?> p) ;
    public List<?> selectSome(Map<?, ?> p) ;
    public ArticleDTO selectOne(ArticleDTO m) ;
    public int count(Map<?, ?> p) ;
    public void update(Map<?, ?> p) ;
    public void delete(Map<?, ?> p) ;
    public String login(ArticleDTO m) ;
    public int  countpage() ;
}
