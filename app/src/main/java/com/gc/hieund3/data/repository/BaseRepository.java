package com.gc.hieund3.data.repository;

import com.gc.hieund3.data.dao.BaseDao;

public interface BaseRepository<Model, DAO extends BaseDao<Model>> {

    void insert(Model model);

    void update(Model model);

    void delete(Model model);

    DAO getDao();

    void onClear();
}
