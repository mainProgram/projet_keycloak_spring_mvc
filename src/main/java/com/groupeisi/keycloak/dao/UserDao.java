package com.groupeisi.keycloak.dao;

import com.groupeisi.keycloak.entity.UserEntity;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class UserDao extends RepositoryImpl<UserEntity> implements IUserDao{

    @Override
    public Optional<UserEntity> login(String email, String password) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cr = cb.createQuery(UserEntity.class);
        Root<UserEntity> user = cr.from(UserEntity.class);
        //Predicate pour la clause where
        Predicate predicateEmail = cb.equal(user.get("username"), email);
        Predicate predicatePwd = cb.equal(user.get("password"), password);
        Predicate finalPredicate = cb.and(predicateEmail, predicatePwd);

        cr.select(user);
        cr.where(finalPredicate);

        return Optional.ofNullable(session.createQuery(cr).getSingleResult());
    }

    @Override
    public Optional<UserEntity> findByUsername(String email) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cr = cb.createQuery(UserEntity.class);
        Root<UserEntity> user = cr.from(UserEntity.class);
        Predicate predicateEmail = cb.equal(user.get("email"), email);
        cr.select(user);

        try {
            UserEntity result = session.createQuery(cr).getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }

    }
}
